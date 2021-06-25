package com.study.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 특정 메서더에 secured 어노테이션 활성화,
                                                                                                                                                     // preAuthorize 어노테이션 활성화

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		        .antMatchers("/user/**").authenticated()
		       // .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		        .antMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
		        .antMatchers("/admin/**").hasRole("ADMIN")
		        .anyRequest().permitAll()  //위의 내용외는 모두 접근 허용함
		        .and()  // 그리고 아래 로긴관련 내용 계속 설정함
		   .formLogin()  //위의 내용으로 들어오지 못하면 => 자동으로 => 이곳 /login이 수행됨
		   .loginPage("/loginForm")  //컨트롤러의 /login 수행됨
		   .loginProcessingUrl("/login") // /login 주소가 호출되면 security가 낚아채서 대신 로긴을 수행함
	       .defaultSuccessUrl("/"); //로긴 성공하면 /로 감	
		        
	}
	
	@Bean //해당 메서드의 리턴되는 객체를 IoC로 등록해 준다.
	public BCryptPasswordEncoder encoderPwd() {
		return new BCryptPasswordEncoder();
	}
//	
//   @Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	     
//	}
}
