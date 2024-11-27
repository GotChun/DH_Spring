package com.example.demo.C04Kakao;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/kakao/pay")
public class KakaoPayController {
    @GetMapping("/main")
    public void main(){
    log.info("GET /kakao/pay/main.,,!!");
    }

    @GetMapping("/req")
    @ResponseBody
    public void req(){
        log.info("GET /kakao/pay/req.,,!!");


        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY DEV501D04AA9E90A1FA68850C439C2ECF05EBDE3"); //한칸 띄우는거 중요 !
        headers.add("Content-Type", "application/json");

        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        JSONObject jobj = new JSONObject();
        jobj.put("cid","TC0ONETIME");
        jobj.put("partner_order_id","partner_order_id");
        jobj.put("partner_user_id","partner_user_id");
        jobj.put("item_name","초코파이");
        jobj.put("quantity","1");
        jobj.put("total_amount","2200");
        jobj.put("vat_amount","200");
        jobj.put("tax_free_amount","0");
        jobj.put("approval_url","http://localhost:8092/kakao/pay/success");  //결제 성공시 이동할 위치
        jobj.put("fail_url","http://localhost:8092/kakao/pay/fail"); //나는 실패하면 이 쪽으로 가도록 매핑해놔서 이렇게 쓰면됨.
        jobj.put("cancel_url","http://localhost:8092/kakao/pay/cancel");

//        params.add("",jobj); 이건 아닌듯 ?


        //HTTP 엔티티(헤더_파라미터)
        HttpEntity<JSONObject> entity = new HttpEntity<>(jobj,headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoPayResponse> response = restTemplate.exchange(url, HttpMethod.POST,entity, KakaoPayResponse.class);
        System.out.println(response.getBody());
//        return response;

    }

    @GetMapping("/success") //결제 성공시
    public void success(){
        log.info("GET /kakao/pay/success.,,!!");
    }

    @GetMapping("/cancel") //결제 취소
    public void cancel(){
        log.info("GET /kakao/pay/cancel.,,!!");
    }

    @GetMapping("/fail") //결제 실패
    public void fail(){
    log.info("GET /kakao/pay/fail.,,!!");
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class KakaoPayResponse  {
        public String tid;
        public boolean tms_result;
        public String created_at;
        public String next_redirect_pc_url;
        public String next_redirect_mobile_url;
        public String next_redirect_app_url;
        public String android_app_scheme;
        public String ios_app_scheme;
    }



}
