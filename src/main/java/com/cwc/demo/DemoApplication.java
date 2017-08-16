package com.cwc.demo;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cwc.demo.model.UserInfo;
@Controller
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.cwc.demo.dao")
@ComponentScan(basePackages = "com.cwc.demo")
@ServletComponentScan(basePackages = "com.cwc.demo")
public class DemoApplication extends SpringBootServletInitializer {
	@RequestMapping("/index")
	public String index(Model model){
		UserInfo single = new UserInfo();
		single.setName("cwc");
		single.setSex("男");
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo u1 = new UserInfo();
		UserInfo u2 = new UserInfo();
		u1.setName("ysc");
		u1.setSex("男");
		u2.setName("lsj");
		u2.setSex("男");
		list.add(u1);
		list.add(u2);
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", list);
		return "index";
		
	}

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
