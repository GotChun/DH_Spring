package com.example.demo.config.auth.provider;

import java.util.Map;

public interface Oauth2UserInfo {

    String getName();
    String getEmail();
    String getProvider();
    String getProviderId();
    Map<String,Object> getAttributes();
    
    
    //유저 정보를 담는 인터페이스를 만들어서 여러 클래스에서 관리할 수 있도록 함

}
