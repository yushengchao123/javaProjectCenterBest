package com.cwc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SpringBootApplication
@EnableAutoConfiguration
//@MapperScan("com.cwc.demo.dao")
//@MapperScan("com.cwc.business.login.dao")
@MapperScan(value={"com.cwc.demo.dao","com.cwc.business.*.dao"})
@ComponentScan(basePackages = "com.cwc")
@ServletComponentScan(basePackages = "com.cwc")
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean  
    public WebMvcConfigurer corsConfigurer() {  
        return new WebMvcConfigurerAdapter() {  
            @Override  
            public void addCorsMappings(CorsRegistry registry) {  
                registry.addMapping("/**");  
            }  
        };  
    }
}
