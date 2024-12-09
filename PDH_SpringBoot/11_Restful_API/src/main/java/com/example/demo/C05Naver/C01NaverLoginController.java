package com.example.demo.C05Naver;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/naver")
public class C01NaverLoginController {

    private final String CLIENT_ID = "T7WFh1YJQyB7TNL0O6g6";
    private final String CALLBACK_URL = "http://localhost:8092/naver/callback";
    private final String CLIENT_SECRET = "LWwWJasVbd";

    private NaverLoginResponse naverLoginResponse;

    @GetMapping("/callback")
    @ResponseBody
    public void naverLogin(@RequestParam("code")String code,@RequestParam("state") String state){
        log.info("GET /naver/callback.,,!! Code = " + code + "state = " + state);

            log.info("GET /kakao/callback.,,!!" + code);

            String url = "https://nid.naver.com/oauth2.0/token";

            //HTTP 요청 헤더
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            //HTTP 요청 파라미터
            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("grant_type","authorization_code");
            params.add("client_id",CLIENT_ID);
            params.add("client_secret",CLIENT_SECRET);
            params.add("code",code); //code 는 인가 코드
            params.add("state",state); //code 는 인가 코드
            //HTTP 엔티티(헤더_파라미터)
            HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
            //HTTP 요청 후 응답받기
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<NaverLoginResponse> response = restTemplate.exchange(url, HttpMethod.POST,entity, NaverLoginResponse.class);
            System.out.println(response.getBody());
//            return response;


    }

    @GetMapping("/login")
    public String login_post(){
        log.info("POST /naver/login.,,!!");


        return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+CLIENT_ID+"&state=STATE_STRING&redirect_uri="+CALLBACK_URL;


    }

    @GetMapping("/unlink")
    public String unlink(){
        log.info("GET 언링크 ㄷㄷ.,,!!");
        return "redirect:https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&access_token="+naverLoginResponse.getAccess_token();
    }

    @GetMapping("/disconnect")
    public String disconn(){
        log.info("GET /naver/disconnect.,,!!");
        return "redirect:https://nid.naver.com/nidlogin.logout?returl=https://www.google.com/";
    }

    @Data
    private static class NaverLoginResponse{
        @JsonProperty(value="access_token")
        public String access_token;
        public String refresh_token;
        public String token_type;
        public String expires_in;
    }



}
//NaverLoginResponse