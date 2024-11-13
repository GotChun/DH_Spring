package com.example.ex01.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
//	@InitBinder
//	public void birthBinder(WebDataBinder webDataBinder) {
//		webDataBinder.registerCustomEditor(LocalDate.class, "birthday", new BirthdayEditor());
//		webDataBinder.registerCustomEditor(String.class, "phone", new PhoneEditor());
		
//	}
	
//	private static class BirthdayEditor extends PropertyEditorSupport{

//
//		@Override
//		public void setAsText(String birthday) throws IllegalArgumentException {
//			// TODO Auto-generated method stub
//			log.info("생일 바꾸기 전 값 : " + birthday);
//			if(birthday.isEmpty()) {
//				log.info("생일 칸이 비어있음. 다시 입력해주셈");
//			}else {
//				birthday = birthday.replaceAll("~", "-");
//				log.info("birthday ~ -> -" + birthday);
//			}
//			
//			setValue(birthday);		//dto 에 할당
//			
//		}
//		
//		
//	}
	
//	private static class PhoneEditor extends PropertyEditorSupport{
//
//		@Override
//		public void setAsText(String phone) throws IllegalArgumentException {
//			// TODO Auto-generated method stub
//			phone = phone.replaceAll("-", "");
//			log.info("phone 바뀐 형식 : " + phone);
//			
//			setValue(phone);	//dto에 할당
//		}
//		
//		
//		
//	}
	
	@GetMapping("/join")
	public void getUser() {
		log.info("GET user /join");
		
	}
	
	@PostMapping("/join")
	public void postUser(@ModelAttribute @Valid UserDto userDto,BindingResult bindingResult,Model model) throws SQLException {
		log.info("post user /join"+userDto);
		if(bindingResult.hasErrors()) {
			for(FieldError error : bindingResult.getFieldErrors()) {
				log.info("에러 : " + error.getField() + " " + error.getDefaultMessage());
				model.addAttribute(error.getField(),error.getDefaultMessage());
			}
		}
		
		userServiceImpl.userRegistration(userDto);

	}
	//@modelAttribute : 이 항목을 Model 
	//@Valid : 이 항목에 대해서 유효성체크를 하겠다는 뜻
	
	
	
}
