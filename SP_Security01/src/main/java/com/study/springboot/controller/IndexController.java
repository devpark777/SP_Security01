package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.model.User;
import com.study.springboot.repository.UserRepository;

@Controller  // view를 리턴한다
public class IndexController {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	  @GetMapping({"/",""})
	  public String index() {
		  // 머스테치를 사용하도록 포함시켜음
		  // 머스테치 기본폴더 : src/main/resources/
		  // 뷰리졸버 설정(자동으로 됨 그래서 생략가능함) : prefix(templates), suffix(.mustache) => application.yml
		  return "index";  //  src/main/resources/templates/index.mustache 
	  }
	  
	  @GetMapping("/user")
	  public String user() {
		  return "user";
	  }
	  
	  @GetMapping("/admin")
	  public String admin() {
		  return "admin";
	  }
	  
	  @GetMapping("/manager")
	  public String manager() {
		  return "manager";
	  }
	  
	  @GetMapping("/loginForm")
	  public String loginForm() {
		  return "loginForm";
	  }
	  
	  @GetMapping("/joinForm")
	  public String joinForm() {
		  return "joinForm";
	  }
	  
	  @PostMapping("/join")
	  public String join(User user) {
		  System.out.println(user);
		  user.setRole("ROLE_USER");
		  //id, createDate은 자동으로 들어감
		  // 패스워드가 아직 암호화가 안되었기에 spring security가 에러 발생시킴
		  String ppp = bCryptPasswordEncoder.encode(user.getPassword());
		  user.setPassword(ppp);
		  
		  userRepository.save(user);
		  return "redirect:/loginForm";
	  }
	 
	  // 하나의 권한에 대해서만 걸때는 아래와 같이 사용하면 됨
	  @Secured("ROLE_ADMIN") // ADMIN 권한 로긴경우만 수행가능
	  @GetMapping("/info")
	  public @ResponseBody String info() {
		  return "개인정보.";
	  }
	  
	  // 권한이 2가지 이상에 대해 설정
	  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // MANAGER or ADMIN 권한 경우
	  @GetMapping("/data")
	  public @ResponseBody String data() {
		  return "데이터정보.";
	  }
	  
}
