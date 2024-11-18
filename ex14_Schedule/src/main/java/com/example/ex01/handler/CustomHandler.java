package com.example.ex01.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CustomHandler implements Controller{	//subController 와 유사함

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CustomHandler's handleRequest()...핸들핸들");
		return null;
	}
	
	public void hello() {
		System.out.println("CustomHandler's hello");
	}

}
