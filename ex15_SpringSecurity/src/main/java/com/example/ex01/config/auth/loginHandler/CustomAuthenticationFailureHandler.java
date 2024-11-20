package com.example.ex01.config.auth.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("CustomAuthenticationFailureHandler 실패실패.."+ exception);
		request.setAttribute("message", "인증실패..! 아이디 , 패스워드 확인하셈");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);	//인증 실패시에 
	}

}
