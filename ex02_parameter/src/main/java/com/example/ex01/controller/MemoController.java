package com.example.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex01.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo") // mapping 기본경로 ?
@Slf4j
public class MemoController {

	@GetMapping("/add") // 이게 서블릿할 때 method가 get방식이라면 실행하는 구문
	public void memo_get() {
		// 메모 추가 페이지 포워딩 핸들러
		log.info("GET /memo/add");

	}



	@PostMapping(value="/add")
	public void memo_post(@ModelAttribute MemoDto memoDto) {  //해야 될것 memo jsp 페이지 만들고 controller 와 매핑, 최종적으로는 이 함수에서 조회되도록
		log.info("POST /memo/post"+memoDto);
		//메모 추가 핸들러
	}
	
	
	
}
