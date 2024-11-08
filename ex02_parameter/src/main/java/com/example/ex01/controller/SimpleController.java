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
		
	@RequestMapping(value="/t1",method=RequestMethod.GET)
	public void t1(Model model) {
		log.info("GET /t1....");
		model.addAttribute("KEY1","VALUE1");
	}
	
	@RequestMapping(value="/t2",method=RequestMethod.GET)
	public String t2() {
		log.info("GET /t2....");
		return "test/test2";
	}
	
	@RequestMapping(value="/t3",method= {RequestMethod.POST,RequestMethod.GET})
	public void t3() {
		log.info("GET /t3....");
	}		

}
