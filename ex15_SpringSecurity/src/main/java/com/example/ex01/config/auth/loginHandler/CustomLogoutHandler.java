package com.example.ex01.config.auth.loginHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("CustomLogout핸들러 인보크.로그아웃됨 !..");
		HttpSession session = request.getSession(false);	//세션이 있으면 그대로 없으면 새로만듬
		if(session!=null) {
			session.invalidate();	//로그아웃을 한다면 세션까지 비워줌
		}
		
		
	}

}
