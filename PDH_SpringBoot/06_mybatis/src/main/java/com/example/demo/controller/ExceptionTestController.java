package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/exTest")

public class ExceptionTestController {
	
//	@ExceptionHandler(FileNotFoundException.class)		//서블릿에 직접 정의했던 예외처리기 , 여기서는 이렇게 만든다 !
//	public String fileNotFoundExceptionHandler(Exception e,Model model) {
//		log.info("에러 : " + e);
//		model.addAttribute("ex",e);
//		
//		return "exTest/error";
//	}
//	
//	@ExceptionHandler(ArithmeticException.class)		//서블릿에 직접 정의했던 예외처리기 , 여기서는 이렇게 만든다 !
//	public String ArithmeticExceptionHandler(Exception e,Model model) {
//		log.info("에러 : " + e);
//		model.addAttribute("ex",e);
//		
//		return "exTest/error";
//	}
//	
//	@GetMapping("/ex1")
//	public void ex1() throws FileNotFoundException {
//		log.info("겟 ex 1`.. ");
//		
//		throw new FileNotFoundException("파일이 없다 ..!");	//일부러 예외 발생시킴
//	}
	
	
	@GetMapping("/ex2/{num}/{div}")
	public String ex2( 
				@PathVariable("num") int num,	//경로의 파라미터를 추가 (경로 변수)
				@PathVariable("div") int div,
				Model model
				) throws Exception 
	{
		log.info("GET.. ex2..num,div  : " + num +"  \n"+ div);
		model.addAttribute("result" , num/div);
		
		return "exTest/ex2";
	}
	
	
	
}
