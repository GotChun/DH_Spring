package com.example.demo.config.auth.jwt;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.JWTToken;
import com.example.demo.domain.entity.Signature;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.JWTTokenRepository;
import com.example.demo.domain.repository.SignatureRepository;
import com.example.demo.domain.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private JWTTokenRepository jwtTokenRepository;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserRepository userRepository;

    //Key 저장
    private Key key;

    public void setKey(Key key){
        this.key = key;
    }
    public JwtTokenProvider(){}
    @PostConstruct
        public void init() {
               List<Signature> list = signatureRepository.findAll();
               if(list.isEmpty()) {  //db에 저장된 key가 없으면}

                   byte[] keyBytes = KeyGenerator.getKeygen();  //키 생성한 값 받아옴
                   this.key = Keys.hmacShaKeyFor(keyBytes);  //키 암호화 시킴
                   Signature signature = new Signature();  //리포지토리 생성
                   signature.setKeyBytes(keyBytes);  //db에 저장
                   signature.setCreate_At(LocalDate.now());
                   signatureRepository.save(signature);  //리포지토리에 저장
                   System.out.println("JwtTokenProvider Constructor 최초 Key init: " + key);   //암호화키?
               }else{
                   Signature signature = list.get(0);
                   byte[] keyBytes = signature.getKeyBytes();
                   this.key = Keys.hmacShaKeyFor(keyBytes);
                   System.out.println("JwtTokenProvider Constructor DB Key init: " + key);
               }
        }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {  //토큰 생성 (로그인된 정보를 받아서)
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = principalDetails.getUserDto();


        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + JwtProperties.EXPIRATION_TIME); // 액세스토큰 만료시간 설정
        String accessToken = Jwts.builder()  //위 
                .setSubject(authentication.getName())
                .claim("username",authentication.getName()) //정보저장
                .claim("auth", authorities)//정보저장
                .claim("principal", authentication.getPrincipal())//정보저장        //jwt 본문에 들어가는 데이터들
                .claim("provider", userDto.getProvider())//정보저장
                .claim("providerId", userDto.getProviderId())//정보저장
                .claim("oauth2AccessToken", principalDetails.getAccessToken())//
                .claim("oauth2Attributes", principalDetails.getAttributes())//

//                .claim("credentials", authentication.getCredentials())//정보저장
//                .claim("details",authentication.getDetails())


                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        Date refreshTokenExpiresIn = new Date(now + JwtProperties.EXPIRATION_TIME_REFRESH); // 리프레쉬 토큰 만료시간 설정
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)  //2분 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .claim("username",authentication.getName()) //정보저장
                .compact();     //리프레쉬 토큰값에도 정보값 넣어줌

        System.out.println("JwtTokenProvider.generateToken.accessToken : " + accessToken);
        System.out.println("JwtTokenProvider.generateToken.refreshToken : " + refreshToken);

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)   //토큰인포로 묶어서 반환
                .refreshToken(refreshToken)
                .build();
    }




    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {  //어덴티케이션 만드는 용도 ?
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());

        String username = claims.getSubject();

        LinkedHashMap principal_tmp = (LinkedHashMap) claims.get("principal");
        String provider = (String) claims.get("provider");
        String providerId = (String) claims.get("providerId");
        String auth = (String) claims.get("auth");
        String oauth2AccessToken = (String) claims.get("oauth2AccessToken");
        LinkedHashMap oauth2Attributes = (LinkedHashMap) claims.get("oauth2Attributes");
//        LinkedHashMap details = (LinkedHashMap) claims.get("details");
//        String credentials = (String) claims.get("credentials");

        System.out.println("principal: " + principal_tmp);
        System.out.println("provider: " + provider);
//        System.out.println("credentials: " + credentials);
//        System.out.println("details: " + details);




        // UserDetails 객체를 만들어서 Authentication 리턴

        PrincipalDetails principalDetails = new PrincipalDetails();
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setRole(auth);
        userDto.setProvider(provider);
        userDto.setProviderId(providerId);

        principalDetails.setUserDto(userDto);
        principalDetails.setAccessToken(oauth2AccessToken);
        principalDetails.setAttributes(oauth2Attributes);

        System.out.println("JwtTokenProvider.getAuthentication UsernamePasswordAuthenticationToken : " + accessToken);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(principalDetails, authorities);
        return usernamePasswordAuthenticationToken;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) throws IOException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        }
        catch (ExpiredJwtException e) {     //액세스 , 리프레쉬 둘다 만료되는지 확인
                                            // 둘 다 만료되거나 , 둘 중 하나만 만료되었는지 확인
            log.info("Expired JWT Token", e);   //액세스 토큰이 만료되었는지 확인
            //리프레쉬 토큰 가져오기. 여기 왔다는거 자체가 토큰이 만료된거라고 ,,
            JWTToken tokenEntity = jwtTokenRepository.findByAccessToken(token);
            if(tokenEntity!=null){  //토큰이 존재한다면 있다면 
                String refreshToken = tokenEntity.getRefreshToken();
                if(refreshToken!=null&&validateToken(refreshToken)){
                    //액세스는 없고 리프레쉬는 있는 경우
                    log.info("리프레쉬값은 존재한다 고로 액세스토큰 다시 발급 ! ");

                    //AccessToken 재발급
                    String username = tokenEntity.getUsername();
                    Optional<User> userOptional = userRepository.findById(username);
                    if(userOptional.isPresent()){
                        User user = userOptional.get();

                        long now = (new Date()).getTime();
                        Date accessTokenExpiresIn = new Date(now + JwtProperties.EXPIRATION_TIME); // 액세스토큰 만료시간 설정
                        String accessToken = Jwts.builder()  //위
                                .setSubject(user.getUsername())
                                .claim("username",user.getUsername()) //정보저장
                                .claim("auth", user.getRole())//정보저장
                                .claim("principal", principalDetails)//정보저장        //jwt 본문에 들어가는 데이터들
                                .claim("provider", userDto.getProvider())//정보저장
                                .claim("providerId", userDto.getProviderId())//정보저장
                                .claim("oauth2AccessToken", principalDetails.getAccessToken())//
                                .claim("oauth2Attributes", principalDetails.getAttributes())//
                                .signWith(key, SignatureAlgorithm.HS256)
                                .claim("username",authentication.getName()) //정보저장
                                .compact();     //리프레쉬 토큰값에도 정보값 넣어줌
                    }

                    Authentication authentication = getAuthentication(refreshToken); //인증정보에
                    TokenInfo newTokenInfo = generateToken(authentication); //새로운 토큰 정보 생성
//                    newTokenInfo.setRefreshToken(refreshToken);

                    tokenEntity.setAccessToken(newTokenInfo.getAccessToken());

                    jwtTokenRepository.save(tokenEntity);
                    //쿠키로 재전달
                    Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenEntity.getAccessToken());
                    cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
                    cookie.setPath("/");
                    response.addCookie(cookie);  //쿠키 저장
                    response.sendRedirect("/");

                    return true;
                }else{
                    //둘다 없는 경우
                    log.info("리프레시 토큰이 만료됨 ! ");
                }
            }else{
                //DB에 내용이 없는경우 액세스가 ?
                log.info("DB에 JWT 정보가 없다! ");
            }
            
            
            
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
}