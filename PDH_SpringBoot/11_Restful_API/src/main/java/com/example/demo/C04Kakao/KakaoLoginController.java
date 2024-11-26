package com.example.demo.C04Kakao;


import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/kakao") //매핑
public class KakaoLoginController {

    String redirect_uri = "http://localhost:8092/kakao/callback";
    String client_id = "7d9934651d7f56d6d199b648ad4852c6";
    String response_Type = "code";
    String LOGOUT_REDIRECT_URI = "http://localhost:8092/main";

    private KakaoResposne kakaoResposne;


//    @GetMapping("/getCode")
    @GetMapping("/login")
    public String getCode(){
        log.info("GET /kakao/getCode.,,!!");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+client_id+"&redirect_uri="+redirect_uri+"&response_type="+response_Type;
    }

    @GetMapping("unlink")
    @ResponseBody
    public void unkink(){
        String url = "https://kapi.kakao.com/v1/user/unlink";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + kakaoResposne.getAccess_token() );

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity entity = new HttpEntity<>(headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("리스폰스 : " +response.getBody());
    }


    @GetMapping("/logout")
    @ResponseBody
    public void logout(){

        log.info("로그아웃 !");

        String url = "https://kapi.kakao.com/v1/user/logout";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoResposne.getAccess_token() );

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity entity = new HttpEntity<>(headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposne = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("리스폰스 : " +resposne.getBody());

    }

    @GetMapping("/logoutAll")
    public void logout_all (HttpServletResponse response) throws IOException {
        log.info("GET /kakao/logoutAll...");
        response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+client_id+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI);
    }

    @GetMapping("/getCodeMsg")
    public String getCodeMsg(){
        log.info("GET /kakao/getCodeMSG .,,!!");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+client_id+"&redirect_uri="+redirect_uri+"&response_type="+response_Type+"&scope=talk_message,friends";
    }

    @GetMapping("/message/me/{message}")
    @ResponseBody
    public void message_me(@PathVariable("message") String message){
        log.info("GET /kakao/message/me.. !"+message);

        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer "+kakaoResposne.getAccess_token());
        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        JSONObject template_object = new JSONObject();
        template_object.put("object_type", "text");
        template_object.put("text", message);
        template_object.put("link", new JSONObject());
        template_object.put("button_title", "버튼제목");

        params.add("template_object", template_object.toString());

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoResposne> response = restTemplate.exchange(url, HttpMethod.POST,entity,KakaoResposne.class);
        System.out.println(response);
    }

    @GetMapping("/message/friends/{message}")
    @ResponseBody
    public void message_friends(@PathVariable("message") String message){
        log.info("GET /kakao 친구에게 보내기..");

        String url = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer "+kakaoResposne.getAccess_token());

        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        JSONObject template_object = new JSONObject();
        template_object.put("object_type", "text");
        template_object.put("text", message);
        template_object.put("link", new JSONObject());
        template_object.put("button_title", "버튼제목");

        List<String> receiver_uuids = new ArrayList();


        params.add("template_object", template_object.toString());

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoResposne> response = restTemplate.exchange(url, HttpMethod.POST,entity,KakaoResposne.class);
        System.out.println(response);
    }


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code){    // 인가 코드를 받을 콜백 함수
        log.info("GET /kakao/callback.,,!!" + code);

        String url = "https://kauth.kakao.com/oauth/token";
        
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",client_id);
        params.add("redirect",redirect_uri);
        params.add("code",code); //code 는 인가 코드
        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoResposne> response = restTemplate.exchange(url, HttpMethod.POST,entity,KakaoResposne.class);
        System.out.println(response);
        this.kakaoResposne = response.getBody();
        return "redirect:/kakao/main";

    }

    @GetMapping("/friends")
    @ResponseBody
    public void friends(){
        log.info("GET /kakao/friends.,,!!");
        String url ="https://kapi.kakao.com/v1/api/talk/friends";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization"," Bearer "+kakaoResposne.getAccess_token());



        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println("친구목록 ?"+response.getBody());

    }

        @GetMapping("/main")
        public void main(){
            log.info("GET /kakao/main.,,!!");
        }

        @GetMapping("/message/friend/{message}")
        @ResponseBody
        public void message_friend(@PathVariable("message") String message){

        }


        @GetMapping("/profile")
        public void profile(Model model){
            log.info("GET /kakao/profile.,,!!");

            String url = "https://kapi.kakao.com/v2/user/me";

            //HTTP 요청 헤더
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            headers.add("Authorization", "Bearer " + kakaoResposne.getAccess_token() );

            //HTTP 엔티티(헤더_파라미터)
            HttpEntity entity = new HttpEntity<>(headers);
            //HTTP 요청 후 응답받기
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ProfileResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ProfileResponse.class);
            System.out.println("리스폰스 : " +response.getBody());
            model.addAttribute("profile",response.getBody());

        }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class KakaoResposne{
        public String access_token;
        public String token_type;
        public String refresh_token;
        public int expires_in;
        public String scope;
        public int refresh_token_expires_in;
    }


    @Data
    private static class KakaoAccount{
        public boolean profile_nickname_needs_agreement;
        public boolean profile_image_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
    }
@Data
    private static class Profile{
        public String nickname;
        public String thumbnail_image_url;
        public String profile_image_url;
        public boolean is_default_image;
        public boolean is_default_nickname;
    }
@Data
    private static class Properties{
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }
@Data
    private static class ProfileResponse{
        public long id;
        public Date connected_at;
        public Properties properties;
        public KakaoAccount kakao_account;
    }





}
