package com.example.demo.controller;


import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

@Autowired
private UserServiceImpl userServiceImpl;



	
	@GetMapping("/join")
	public void getUser() {
		log.info("GET user /join");
		
	}

	@PostMapping("/join")
	public void postUser(UserDto userDto) throws SQLException {
		log.info("POST user /join"+userDto);

		userServiceImpl.memberJoin(userDto);

	}

//
//	@PostMapping("/join")
//	public void postUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
//		log.info("post user /join"+userDto);
//		if(bindingResult.hasErrors()) {
//			for(FieldError error : bindingResult.getFieldErrors()) {
//				log.info("에러 : " + error.getField() + " " + error.getDefaultMessage());
//				model.addAttribute(error.getField(),error.getDefaultMessage());
//			}
//		}
//
//	}
	//@modelAttribute : 이 항목을 Model 
	//@Valid : 이 항목에 대해서 유효성체크를 하겠다는 뜻
	
	
	
}
