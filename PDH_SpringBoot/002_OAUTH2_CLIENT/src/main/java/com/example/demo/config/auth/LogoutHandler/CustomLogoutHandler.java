package com.example.demo.config.auth.LogoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
public class CustomLogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {
    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("logout..!!");//이게 로그아웃을 시도 할떄인가 ? //맞네 로그아웃 처리를 받는 함수임.이거는
        HttpSession httpSession= request.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
                String accessToken = (String) authentication.getCredentials();
    }




    //-----------------위에 뭐 더 써야함

    System.out.println(resp.getBody());
    else if(provider!=null && provider.startsWith("google")){
        //URL
        String url ="https://accounts.google.com/o/oauth2/revoke?token="+accessToken;
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        //PARAM
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        //ENTITY
        HttpEntity

    }





}
