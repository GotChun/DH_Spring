package com.example.demo.config.auth.LogoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.domain.entity.JWTToken;
import com.example.demo.domain.repository.JWTTokenRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class CustomLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    String KAKAO_CLIENT_ID;
    @Value("${spring.security.oauth2.client.kakao.logout.redirect.uri}")
    String KAKAO_LOGOUT_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JWTTokenRepository jwtTokenRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomLogoutSuccessHandler's onLogoutSuccess..invoke..");



        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
                .map(cookie->cookie.getValue())
                .orElse(null);
        if(token!=null){
            authentication = jwtTokenProvider.getAuthentication(token);
        }


        //JWT TOKEN 삭제
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();   //로그아웃이 된 상태인데 authentication 을 요청하니까 null 이라고 뜸
        String provider = principalDetails.getUserDto().getProvider();  // provider 정보를 복원해서 다시 비교해야되서 정보 꺼내옴

        //DB JWT 삭제
        JWTToken jwtToken = jwtTokenRepository.findByUsername(authentication.getName());
        jwtTokenRepository.deleteById(jwtToken.getId());  //로그아웃 했을 때 리프레쉬 토큰도 삭제되게 함.
//        if(jwtToken!=null&& jwtTokenProvider.validateToken(jwtToken.getAccessToken())){ //토큰이 존재하는데 토큰 값이 비교해서 안맞을 때
//            jwtTokenRepository.deleteById(jwtToken.getId()); //
//            log.info("로그아웃 , 액세스 리프레쉬 둘다 만료 , 디비에서 제거");
//        }else{
//            log.info("리프레쉬에 의해 다시발급 DB에 저장");
//        }


        if(provider!=null && provider.equals("kakao")){
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
