package com.example.demo.config.auth.LogoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.io.IOException;

@Slf4j
public class CustomLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler { //LogoutSuccessHandler 이라는 존재하는 class 를 정의하는구나

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    String KAKAO_CLIENT_ID;
    @Value("${spring.security.oauth2.client.kakao.logout.redirect.uri}")        //프로퍼티 파일에 있는 값을 동적으로 할당하는법
    String KAKAO_LOGOUT_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomLogoutSuccessHandler's onLogoutSuccess..invoke.."); //로그아웃에 성공했을 때 실행되는 메서드이다.

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); //principal 객체 정보를 가져온다 ?
        String provider = principalDetails.getUserDto().getProvider();  //제공자 정보를 가져옴 (kakao , naver , google)

        if(provider!=null && provider.equals("kakao")){     //제공자 명이 kakao 라면 리다이렉트 실행
            response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+KAKAO_CLIENT_ID+"&logout_redirect_uri="+KAKAO_LOGOUT_REDIRECT_URI);
            return ;
        }else if(provider!=null && provider.equals("naver")){
            response.sendRedirect("https://nid.naver.com/nidlogin.logout?returl=https://www.naver.com/");
            return ;
        }else if(provider!=null && provider.equals("google")){
            response.sendRedirect("https://accounts.google.com/Logout");
            return ;
        }
        response.sendRedirect("/");
    }

}