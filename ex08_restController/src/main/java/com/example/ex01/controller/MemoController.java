package com.example.ex01.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")		//mapping 기본경로 ?
@Slf4j
public class MemoController {

	@Autowired
	public MemoServiceImpl memoService;
	
	
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		log.info("MEMOContorller's dataBinder " + webDataBinder);
//		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest", new DateTestEditor());  	//dateTest 라는 name 의 파라미터값을 LocalDate 타입으로 바꿔준다
	}
	
//	private static class DateTestEditor extends PropertyEditorSupport{		//내부에서 사용할 class 선언
//
//		@Override
//		public void setAsText(String dateTest) throws IllegalArgumentException {
//			// TODO Auto-generated method stub
//		log.info("dateTestEditor's setAsText invoke "+dateTest);	
//		if(dateTest.isEmpty()) {
//			dateTest=LocalDate.now().toString();	
//		}else {
//			dateTest = dateTest.replaceAll("#", "-");		//# 문자를 - 로 바꾼다
//			log.info("dateTest # -> :" + dateTest);
//			
//		}
//		
//		LocalDate date = LocalDate.parse(dateTest,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
////		log.info("parsed date : " + date);
//		setValue(date);
//		}
//	
//		
//	}
	
	
	
	@GetMapping("/add")			//이게 서블릿할 때 method가 get방식이라면 실행하는 구문
	public void memo_get() {
		//메모 추가 페이지 포워딩 핸들러
		log.info("GET /memo/add");
		
	}
	
	@PostMapping(value="/add")
	public void memo_post(@ModelAttribute @Valid MemoDto memoDto,BindingResult bindingResult,Model model) throws Exception  //바인딩 결과를 받는 lib  ,, 객체 입력 위치도 중요함 , model 이 앞에가면 오류가 남
	{ //해야 될것 memo jsp 페이지 만들고 controller 와 매핑, 최종적으로는 이 함수에서 조회되도록
	  log.info("POST /memo/post"+memoDto); //메모 추가 핸들러 }
	 if(bindingResult.hasErrors()) {
//		 log.info("데이터 유효성 체크 오류" + bindingResult.getFieldError("id").getDefaultMessage() );  //getDefaultMessage ? 기본 입력한 오류문만 나옴
//		 model.addAttribute("id",bindingResult.getFieldError("id").getDefaultMessage());	//model 객체가 서블릿할 때 
		 for(FieldError error : bindingResult.getFieldErrors()) {
			 log.info("Error Field :"+ error.getField()+ " " + error.getDefaultMessage());
			 model.addAttribute(error.getField(),error.getDefaultMessage());
		 }
	 }
	  
	 //서비스 실행 posting
	 memoService.memoRegistration(memoDto);
	 model.addAttribute("memoDto",memoDto);
	 
	}
	
	@GetMapping("/rest")
	public void rest_test_page() {
		///메모추가 페이지 포워딩 핸들러
		log.info("GET /memo/rest...");
	}
	
	
	@GetMapping("/restTest")				//이렇게 해도 되는데 뭐 이런방식은 별로 안좋다 ?
	public @ResponseBody String restTest() {
		log.info("rest/test");
		return "TEST";
	}
	
	
	
	
}
