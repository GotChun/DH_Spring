package com.example.demo.C07PortOne;

import com.example.demo.C04Kakao.KakaoLoginController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/portOne")
public class PortOneController {

    private TokenResponse tokenResponse;

    @GetMapping("/main")
    public void main(){
        log.info("GET /portone포트원 !.,,!!");
    }

    @GetMapping("/token")
    public void getToken(){
        log.info("GET /portone토��요청 !.,,!!");


        String url = "https://api.iamport.kr/users/getToken";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
        //HTTP 요청 파라미터

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>() {
        };
        
        params.add("imp_key", "7024778378801746");
        params.add("imp_secret","74GZZcXgu4eC1U3AyG1gB9BN0ssvOYaUYSXnRt1jdLbo4jSSV52VM4WJcKrO6vYoNXJL2Mv2k5y4FO81");

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TokenResponse> response = restTemplate.exchange(url, HttpMethod.POST,entity, TokenResponse.class);
        System.out.println(response.getBody());
        this.tokenResponse = response.getBody();
    }

    @GetMapping("/getPayments")
    public void getPayments(){
        log.info("GET /portone결제요청 !.,,!!");

        String url = "https://api.iamport.kr/payments?imp_uid[]=imp_863673229633&merchant_uid[]=INIpayTest";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + tokenResponse.getResponse().getAccess_token());
        //HTTP 요청 파라미터


        //HTTP 엔티티(헤더_파라미터)
        HttpEntity      entity = new HttpEntity<>(headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println(response.getBody());
    }


    @Data
    private static class Response{
        public String access_token;
        public int now;
        public int expired_at;
    }

    @Data
    private static class TokenResponse{
        public int code;
        public Object message;
        public Response response;
    }

}
