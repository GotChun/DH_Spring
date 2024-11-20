package com.example.ex01.config.auth.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {		//인증을 받지않은 사용자가 해당 시스템에 접근하려 할때 메시지를 띄움

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("CustomAuthenticationEntryPoint's commence()./.. !1dasdsad" + authException);
		request.setAttribute("message", "인증이 필요한 페이지임 ! ");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);	//메시지를 띄우고 로그인 페이지로 포워딩처리함
	}

}
