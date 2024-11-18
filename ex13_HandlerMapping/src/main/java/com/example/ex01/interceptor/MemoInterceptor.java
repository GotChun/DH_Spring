package com.example.ex01.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MemoInterceptor implements HandlerInterceptor{
	
	//메모컨트롤러로 이동하기 전 처리 로직
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MEMOInterceptor preHandle.." );
		return true;
	}

	//메모컨트롤러 작업끝난이후 처리로직
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MEMOInterceptor postHandler");
		
	}
	
	
	//View page 렌더링 된 이후 처리로직
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("MEMOInterceptor afterCompletion invoke..");

	}


	
	
}
