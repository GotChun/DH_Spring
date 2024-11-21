package com.example.demo.controller.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	
	
	
	@ExceptionHandler(Exception.class)		//서블릿에 직접 정의했던 예외처리기 , 여기서는 이렇게 만든다 !
	public String AllExceptionHandler(Exception e,Model model) {
		log.info("에러 : " + e);
		model.addAttribute("ex",e);
		
		return "globalError";
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)		//서블릿에 직접 정의했던 예외처리기 , 여기서는 이렇게 만든다 !
	public String GlobalAllExceptionHandler(Exception e,Model model) {
		log.info("에러 : " + e);
		model.addAttribute("ex",e);
		
		return "globalError";
	}
}
