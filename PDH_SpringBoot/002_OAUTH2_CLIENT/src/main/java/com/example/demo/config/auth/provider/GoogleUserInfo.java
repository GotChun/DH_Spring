package com.example.demo.config.auth.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUserInfo implements Oauth2UserInfo {
    private String id;
    private Map<String,Object> attributes;



    @Override
    public String getName() {  //유저 정보를 꺼낼 메서드
        return (String)attributes.get("name");      //카카오 property 부분에 정보를 가져올 예정
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return this.id;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return Map.of();
//    }
}
