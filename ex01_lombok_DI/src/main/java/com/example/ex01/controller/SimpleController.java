package com.example.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class SimpleController {

	@RequestMapping(value = "/t1",method=RequestMethod.GET)		//t1이라는 이름의 매핑 이름
	public void t1(Model model) {
		log.info("GET /t1..");	
		model.addAttribute("KEY1","VALUE1");
	}
	
	@RequestMapping(value = "/t2",method=RequestMethod.POST)
	public String t2() {
		log.info("GET /t2..");	
		return "test/test2";			//test2 번 파일로 이동시켜준다.
	}
	
	@RequestMapping(value = "/t3",method= {RequestMethod.GET,RequestMethod.POST})
	public void t3() {
		log.info("GET /t3..");	
	}
	
}