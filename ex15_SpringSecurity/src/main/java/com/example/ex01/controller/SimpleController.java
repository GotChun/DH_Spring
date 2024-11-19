package com.example.ex01.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SimpleController {
	
//	@GetMapping("/user")
//	public void user(Authentication ahthentication) {	//ahthentication 클래스로 아이디 패스워드를 담게되고
//		log.info("GET /user..." + ahthentication);
//		log.info("GET /user..." + ahthentication.getName());	//유저명
//		log.info("GET /user..." + ahthentication.getPrincipal());	//
//		log.info("GET /user..." + ahthentication.getAuthorities());	//권한명
//		log.info("GET /user..." + ahthentication.getDetails());		//리퀘스트의 내용들
//		log.info("GET /user..." + ahthentication.getCredentials());		//패스워드 인증정보
//		
//	}
	
//	@GetMapping("/user")
//	public void user(@AuthenticationPrincipal Principal principal) {	
//		log.info("GET /user... " + principal);		//	사용자의 식별정보를 확인
//		
//	}
//	
//	@GetMapping("/user")
//	public void user(@AuthenticationPrincipal Principal principal) {	//security context holder를 건드는 방법
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);			//위 Principal 과 같은 내용이 나옴..
//	}
	@GetMapping("/user")
	public void user() {	
		log.info("GET /user..jstl 로 auth 확인");	
	}
	@GetMapping("/member")
	public void member() {
		log.info("GET /member...");
	}
	@GetMapping("/admin")
	public void admin() {
		log.info("GET /admin...");
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("GET /login...");
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("GET /join..");
	}
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/join")
	public String join_post(UserDto userDto) {
		log.info("POST /join..");
		userServiceImpl.memberJoin(userDto);
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public void logout() {
		log.info("GET logout");
	}
	
	
	
	
	
}