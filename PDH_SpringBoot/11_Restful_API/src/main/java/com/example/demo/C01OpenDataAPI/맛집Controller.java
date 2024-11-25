package com.example.demo.C01OpenDataAPI;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/opendata")
public class 맛집Controller {


    @GetMapping(value = "/food",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<맛집Root> food() throws URISyntaxException {
        log.info("GET .. OPNE DATA APT");
        String url = "https://api.odcloud.kr/api/15052653/v1/uddi:af9a76e4-c949-4712-91c5-ecdb41d669fe_201809211854";
        String pageNo ="1";
        String perPage="100";
        String returnType = "json";
        String serviceKey = "xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";

        url += "?page="+pageNo;
        url += "&perPage="+perPage;
        url += "&returnType="+returnType;
        url += "&serviceKey="+serviceKey;
        System.out.println(url);

        //Request Header 설정
        
        //Request Parameter 설정
        
        //Request Entity 로 묶기
        
        // 요청 -> 응답 처리
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<맛집Root> response=
            restTemplate.exchange(url, HttpMethod.GET,null,맛집Root.class);



        System.out.println("response"+ response.getBody());

        return response;
    }

    @GetMapping(value="/weather/{base_date}/{base_time}/{nx}/{ny}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WeatherRoot> weather(
            @PathVariable("base_date") String base_date,
            @PathVariable("base_time") String base_time,
            @PathVariable("nx") String nx,
            @PathVariable("ny") String ny

    ){
        log.info("GET .. weather !");
        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String numOfRows = "10";
        String pageNo ="1";
//         base_date = "20241125";
//         base_time = "0600";
//         nx = "55";
//         ny = "127";
        String dataType = "json";
        String serviceKey = "xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";

        url += "?numOfRows="+numOfRows;
        url += "&pageNo="+pageNo;
        url += "&base_date="+base_date;
        url += "&base_time="+base_time;
        url += "&nx="+nx;
        url += "&ny="+ny;
        url += "&dataType="+dataType;
        url += "&serviceKey="+serviceKey;
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherRoot> response=
                restTemplate.exchange(url, HttpMethod.GET,null,WeatherRoot.class);
        //exchage 메서드 : 지정된 URL 로 사용자 HTTP 요청을 보낸다. (호출할 API URL , HTTp 메서드지정 , 추가로 보낼 http 헤더(현재는 null값 , 응답상태코드 HTTP 헤더
        System.out.println("reponse" +response.getBody());

        return response;
    }

    @GetMapping("/bus")
    @ResponseBody
    public ResponseEntity<String> bus(){
        String url = "https://apis.data.go.kr/6270000/dbmsapi/realtime?serviceKey=xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=
                restTemplate.exchange(url, HttpMethod.GET,null,String.class);
        System.out.println("response"+response.getBody());
        return response;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class 맛집Datum{
        public String 업소명;
        public String 연번;
        public String 전화번호;
        public String 주메뉴;
        public String 주소;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class 맛집Root{
        public int currentCount;
        public ArrayList<맛집Datum> data;
        public int matchCount;
        public int page;
        public int perPage;
        public int totalCount;
    }
    @Data
    private static class Body{
        public String dataType;
        public Items items;
        public int pageNo;
        public int numOfRows;
        public int totalCount;
    }
    @Data
    private static class Header{
        public String resultCode;
        public String resultMsg;
    }
    @Data
    private static class Item{
        public String baseDate;
        public String baseTime;
        public String category;
        public int nx;
        public int ny;
        public String obsrValue;
    }
    @Data
    private static  class Items{
        public ArrayList<Item> item;
    }
    @Data
    private static class Response{
        public Header header;
        public Body body;
    }
    @Data
    private static class WeatherRoot{
        public Response response;
    }

    private static class header {
        public boolean success;
        public int resultCode;
        public String resultMsg;
    }

    private static class arrList {
        public double routeId;
        public int routeNo;
        public int moveDir;
        public int bsGap;
        public String bsNm;
        public int vhcNo2;
        public String busTCd2;
        public String busTCd3;
        public String busAreaCd;
        public String arrState;
        public int prevBsGap;
        public List<arrList> arrList;
    }

    private static class items {
        public int routeNo;
        public arrList arrList;
    }

    private static class body {
        public items items;
        public int totalCount;
    }

    private static class Result {
        public header header;
        public body body;
    }





}


