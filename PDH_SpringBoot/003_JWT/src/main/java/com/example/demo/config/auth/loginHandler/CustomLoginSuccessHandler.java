package com.example.demo.config.auth.loginHandler;

import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.TokenInfo;
import com.example.demo.domain.entity.JWTToken;
import com.example.demo.domain.repository.JWTTokenRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
public class CustomLoginSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    private JWTTokenRepository jwtTokenRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomLoginSuccessHandler's onAuthenticationSuccess invoke...");

        //JWTTOKEN Entity 에 기존의 발급받은 토큰이 있는지 ? 확인
        //없다면 새로 발급 , 있다면 DB에서 꺼내와서 사용
        String username = authentication.getName();
       JWTToken dbToken = jwtTokenRepository.findByUsername(username); //이게 db랑 비교하는 값 ?
        if(dbToken !=null){ //토큰이 널이 아닌경우 = 데이터가 있다는 뜻 !

            if(jwtTokenProvider.validateToken(dbToken.getAccessToken())){
//                JWTToken newToken = jwtTokenRepository.findByUsername(username);
//
//                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,newToken.getAccessToken());
//                cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
//                cookie.setPath("/");
//                response.addCookie(cookie);
                System.out.println("DB에 있는 accessToken 을 전달");
            }
            else{  //둘 다 만료된경우 기존 DB에있는 토큰 데이터는 삭제
                jwtTokenRepository.deleteById(dbToken.getId());
                 TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
                System.out.println("토큰 인포 ? : "+tokenInfo);
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken());
                cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
                cookie.setPath("/");
                response.addCookie(cookie);  //쿠키 저장
                System.out.println("새로 액세스 토큰 발급 !");
                JWTToken jwtToken = JWTToken.builder()  //DB에 저장하기 위함
                        .accessToken(tokenInfo.getAccessToken())    //토큰 객체 생성
                        .refreshToken(tokenInfo.getRefreshToken())
                        .username(username)
                        .issuedAt(LocalDateTime.now())
                        .build();
                jwtTokenRepository.save(jwtToken);  //DB 저장
                System.out.println("둘다 토큰 만료됐음 .. DB 기존토큰삭제 , 새롭게 토큰정보 생성 DB 저장");
                
            }
        }else{
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            System.out.println("토큰 인포 ? : "+tokenInfo);
            Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken());
            cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
            cookie.setPath("/");
            response.addCookie(cookie);  //쿠키 저장

            JWTToken jwtToken = JWTToken.builder()  //DB에 저장하기 위함
                                        .accessToken(tokenInfo.getAccessToken())    //토큰 객체 생성
                    .refreshToken(tokenInfo.getRefreshToken())
                    .username(username)
                    .issuedAt(LocalDateTime.now())
                    .build();
            jwtTokenRepository.save(jwtToken);  //DB 저장
            System.out.println("최초 로그인 , DB JWT 저장 , JWT 쿸 ㅣ전달");
        }


        log.info("CustomLoginSuccessHandler's onAuthentication'");
        response.sendRedirect("/");

    }
}
