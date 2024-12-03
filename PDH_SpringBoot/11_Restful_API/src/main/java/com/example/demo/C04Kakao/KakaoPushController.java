package com.example.demo.C04Kakao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/kakao")
public class KakaoPushController {


    @GetMapping("/push/token")
    public void pushToken(){

        String url = "https://kapi.kakao.com/v2/push/register";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","363a3a3a52af9dcd40ac5f9f0335991e");
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //요청 파라미터


    }
}
