package com.example.ex01.config.auth.loginHandler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSucccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("CustomLoginSuccessHandler's onAuthenticationsuccess() invoke나나나나~");
		
		Collection<? extends GrantedAuthority> authorites= authentication.getAuthorities();	//컬렉션형태로 role이 담겨있다.
		authorites.forEach(role->{
			System.out.println("ROLE : " + role.getAuthority());	//어떤 역할인지 꺼내서 볼 수 있음
		try {
			if("ROLE_ADMIN".equals(role.getAuthority())) {				//각 권한일시에 리다이렉팅
				response.sendRedirect(request.getContextPath()+"/admin");
				return ;
			}else if("ROLE_MEMBER".equals(role.getAuthority())) {
				response.sendRedirect(request.getContextPath()+"/member");
				return ;
			}else if("ROLE_USER".equals(role.getAuthority())) {
				response.sendRedirect(request.getContextPath()+"/user");
				return ;
			}else {
				response.sendRedirect(request.getContextPath()+"/");
				return ;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		});
	}

}
