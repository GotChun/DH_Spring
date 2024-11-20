package com.example.ex01.config.auth.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		System.out.println("CustomAccessDeinedHandler 뭐뭐무머 : " +accessDeniedException);
		request.setAttribute("error", accessDeniedException);
		request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
	}

}
