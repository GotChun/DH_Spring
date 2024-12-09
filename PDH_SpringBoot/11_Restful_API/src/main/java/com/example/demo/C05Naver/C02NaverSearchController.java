package com.example.demo.C05Naver;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/naver")
public class C02NaverSearchController {

    private final String CLIENT_ID = "T7WFh1YJQyB7TNL0O6g6";
    private final String CALLBACK_URL = "http://localhost:8092/naver/callback";
    private final String CLIENT_SECRET = "LWwWJasVbd";

    @GetMapping("/book/{keyword}")
    @ResponseBody
    public String search(@RequestParam("keyword") String keyword){
        log.info("GET /naver/search/01.,,!!"+keyword);

        String url = "https://openapi.naver.com/v1/search/book.json?query="+keyword;

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id",CLIENT_ID );
        headers.add("X-Naver-Client-Secret",CLIENT_SECRET );
        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//        params.add("query",keyword);

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println(response);

        return response.getBody();

    }

}
