package com.example.demo.config.auth.LogoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public class CustomLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String CLIENT_ID ;
    @Value("${spring.security.oauth2.client.kakao.logout.redirect.uri}")
    String LOGOUT_REDIRECT_URL;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String provider = principalDetails.getUserDto().getProvider();

        if(provider!= null && provider.equals("kakao")){
            response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+ CLIENT_ID+"&redirect_uri="+LOGOUT_REDIRECT_URL);
            return ;
        }else if(provider!= null && provider.equals("naver")){
            response.sendRedirect("https://nid.naver.com/nidlogin.logout?returl=https://www.naver.com/");
         
            return ;
        }else if(provider!= null && provider.equals("google")){
            response.sendRedirect("https//accounts.google.com/logout");
            return ;
        }

        response.sendRedirect("/");
    }
}
