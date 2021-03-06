package com.cwc.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cwc.common.utils.MD5Util;

import org.springframework.http.HttpMethod;  
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        //csrf().disable().(关闭防跨域攻击功能，使用 http.csrf().disable()：)
        http.csrf().disable().
                 authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/interfaces/*").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/showtime")
                .defaultSuccessUrl("/main")//登录成功后默认跳转到"/main"
                .failureUrl("/403")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index")//退出登录后的默认url是"/home"
                .permitAll();
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(

            new PasswordEncoder(){

                @Override
                public String encode(CharSequence rawPassword) {
                    return MD5Util.encode((String)rawPassword);
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return encodedPassword.equals(MD5Util.encode((String)rawPassword));
                }}); //user Details Service验证
        
        
        //auth.inMemoryAuthentication().withUser("cwc").password("123456").roles("ROLE_ADMIN");
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();

    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

}
