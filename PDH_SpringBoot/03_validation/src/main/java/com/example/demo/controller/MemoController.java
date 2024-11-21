package com.example.demo.controller;


import com.example.demo.domain.dto.MemoDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/memo") // mapping 기본경로 ?
@Slf4j
public class MemoController {


	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		log.info("MEMOContorller's dataBinder " + webDataBinder);
		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest", new DateTestEditor());  	//dateTest 라는 name 의 파라미터값을 LocalDate 타입으로 바꿔준다
	}

	private static class DateTestEditor extends PropertyEditorSupport {		//내부에서 사용할 class 선언

		@Override
		public void setAsText(String dateTest) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			log.info("dateTestEditor's setAsText invoke "+dateTest);
			if(dateTest.isEmpty()) {
				dateTest=LocalDate.now().toString();
			}else {
				dateTest = dateTest.replaceAll("#", "-");		//# 문자를 - 로 바꾼다
				log.info("dateTest # -> :" + dateTest);

			}

			LocalDate date = LocalDate.parse(dateTest, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		log.info("parsed date : " + date);
			setValue(date);
		}


	}

	@GetMapping("/add") // 이게 서블릿할 때 method가 get방식이라면 실행하는 구문
	public void memo_get() {
		// 메모 추가 페이지 포워딩 핸들러
		log.info("GET /memo/add");

	}



	@PostMapping(value="/add")
	public void memo_post(@ModelAttribute @Valid MemoDto memoDto, BindingResult bindingResult, Model model)  //바인딩 결과를 받는 lib  ,, 객체 입력 위치도 중요함 , model 이 앞에가면 오류가 남
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

	}








}
