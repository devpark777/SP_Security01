package com.study.springboot.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver; //이것이 맞음
//import org.springframework.boot.web.reactive.result.view.MustacheViewResolver; 주의 이것 아님 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
	 @Override
	 public void configureViewResolvers(ViewResolverRegistry registry) {
		 MustacheViewResolver resolver = new MustacheViewResolver();
		 resolver.setCharset("UTF-8");
		 resolver.setContentType("text/html;charset=UTF-8");
		 resolver.setPrefix("classpath:/templates/");
		 resolver.setSuffix(".html"); //머스테치의 뷰리졸버가 .html 동작하도록 함
		 
		
		 
		 registry.viewResolver(resolver);
		 
	 }
}
