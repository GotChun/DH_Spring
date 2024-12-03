package com.example.demo.config.auth;

import com.example.demo.config.auth.provider.GoogleUserInfo;
import com.example.demo.config.auth.provider.KakaoUserInfo;
import com.example.demo.config.auth.provider.NaverUserInfo;
import com.example.demo.config.auth.provider.Oauth2UserInfo;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//REST 하게 DB 에서 정보 요청
@Service
public class PrincipalDetailsOauth2ServiceImpl extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println();
        System.out.println("PrincipalDetailsOauth2ServiceImpl.loadUser()"+userRequest);
        System.out.println("레지스트레이션 "+userRequest.getClientRegistration());
        System.out.println();
        System.out.println("억세스 토큰 : "+userRequest.getAccessToken());
        System.out.println();
        System.out.println("토큰 파라미터 : "+userRequest.getAdditionalParameters());
        System.out.println();
        System.out.println("토큰 값  : "+userRequest.getAccessToken().getTokenType().getValue());
        System.out.println();
        System.out.println( "스코프 뭐시기 : "+userRequest.getAccessToken().getScopes());
        System.out.println();

        //oauthUserInfo
        OAuth2User oAuthUser = super.loadUser(userRequest);
        System.out.println();
        System.out.println("OAUTH2 : " + oAuthUser);
        //OAUTH2 SERVER 구별
        Oauth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();  //클라이언트 ID 가져옴
        if(provider.startsWith("kakao")){
            String id = oAuthUser.getAttributes().get("id").toString(); //id 정보 뽑아옴
            Map<String,Object> attributes = (Map<String, Object>) oAuthUser.getAttributes().get("properties");  //Map 형태로 받아오는듯 ?
//            System.out.println("속성 ? : "+attributes);
//            System.out.println("아이디 ? : "+id);
            oAuth2UserInfo = new KakaoUserInfo(id,attributes);
            System.out.println("뭐임 ? : "+oAuth2UserInfo);
        }
        else if(provider.startsWith("naver")){

                    ; //id 정보 뽑아옴
            Map<String,Object> attributes = (Map<String, Object>) oAuthUser.getAttributes().get("response");  //Map 형태로 받아오는듯 ?
            String id = attributes.get("id").toString();        //response 안에 id value 값을 받아옴
            System.out.println("속성 ? : "+attributes);
            System.out.println("아이디 ? : "+id);
            oAuth2UserInfo = new NaverUserInfo(id,attributes);
            System.out.println("뭐임 ? : "+oAuth2UserInfo);
        }
        else if(provider.startsWith("google")){

            ; //id 정보 뽑아옴
            Map<String,Object> attributes = (Map<String, Object>) oAuthUser.getAttributes();  //Map 형태로 받아오는듯 ?
            String id = attributes.get("sub").toString();        //response 안에 id value 값을 받아옴
            System.out.println("속성 ? : "+attributes);
            System.out.println("아이디 ? : "+id);
            oAuth2UserInfo = new GoogleUserInfo(id,attributes);
            System.out.println("뭐임 ? : "+oAuth2UserInfo);
        }


        //DB 조회
        String username = oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId();  //계정명 임시 지정
        String password = passwordEncoder.encode("1234");  //패스워드 지정
        Optional<User> userOptional = userRepository.findById(username);

        UserDto userDto = null;
        

        if(userOptional.isPresent()){  //optional 객체가 값을 가지고 있다면  true or false = 기존 계정이 있다면 ?
            //기존 계정 존재 여부 Entity -> dto
            User user = userOptional.get();     //userOptional 에서 findByid 로 username 을 가져온다음
            userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDto.setProvider(user.getProvider());
            userDto.setProviderId(user.getProviderId());
        }else{
            //새로운 계정 DB 저장

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole("ROLE_USER");
            user.setProvider(oAuth2UserInfo.getProvider());
            user.setProviderId(oAuth2UserInfo.getProviderId());
            userRepository.save(user);

            userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setRole("ROLE_USER");
            userDto.setProvider(oAuth2UserInfo.getProvider());
            userDto.setProviderId(oAuth2UserInfo.getProviderId());

        }



             //요청을 하고 나온 결과값

        PrincipalDetails principalDetails = new PrincipalDetails();

//        UserDto userDto = new UserDto();
//        userDto.setRole("ROLE_USER");
//        userDto.setUsername("ABCD");

        principalDetails.setUserDto(userDto);
        principalDetails.setAttributes(oAuthUser.getAttributes());      //변경예정
        principalDetails.setAccessToken(userRequest.getAccessToken().getTokenValue());
        return principalDetails;
    }
}
