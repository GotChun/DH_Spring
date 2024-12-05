package com.example.demo.config;

import com.example.demo.config.auth.LogoutHandler.CustomLogoutHandler;
import com.example.demo.config.auth.LogoutHandler.CustomLogoutSuccessHandler;
import com.example.demo.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.demo.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.demo.config.auth.jwt.JwtAuthorizationFilter;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.demo.config.auth.loginHandler.CustomLoginSuccessHandler;

import com.example.demo.domain.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {

        //CSRF 비활성화
        http.csrf((config)->{config.disable();});

        //권한 체크
        http.authorizeHttpRequests((auth)->{

            auth.requestMatchers("/","/join","/login").permitAll();
            auth.requestMatchers("/user").hasRole("USER");
            auth.requestMatchers("/member").hasRole("MEMBER");
            auth.requestMatchers("/admin").hasRole("ADMIN");
            auth.anyRequest().authenticated();
        });

        //로그인
        http.formLogin((login)->{
            login.permitAll();
            login.loginPage("/login");
            login.successHandler(customLoginSuccessHandler());
            login.failureHandler(new CustomAuthenticationFailureHandler());
        });

        //로그아웃
        http.logout((logout)->{
            logout.permitAll();
            logout.logoutUrl("/logout");
            logout.addLogoutHandler(customLogoutHandler());
            logout.logoutSuccessHandler(customLogoutSuccessHandler());
        });

        //예외처리
        http.exceptionHandling((exception)->{
            exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
            exception.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        //REMEMBER ME
        http.rememberMe((rm)->{
            rm.rememberMeParameter("remember-me");
            rm.alwaysRemember(false);
            rm.tokenValiditySeconds(30*30);
            rm.tokenRepository(tokenRepository());
        });

        //OAUTH2-CLIENT
        http.oauth2Login((auth)->{
            auth.loginPage("/login");   //auth2 로그인페이지
            auth.successHandler(customLoginSuccessHandler());
        });


        //SESSION INVALIDATE
        http.sessionManagement(
                httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
        );

        //JWT AUTH FILTER ADDED
        http.addFilterBefore(new JwtAuthorizationFilter(userRepository,jwtTokenProvider),
                BasicAuthenticationFilter.class);

        //OAUTH2 SESSION 쿠키 제거필터
        http.addFilterBefore(sessionRemoveFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        userDetailsManager.createUser(
//                User.withUsername("user")
//                       .password(passwordEncoder.encode("1234"))
//                       .roles("USER")
//                       .build()
//        );
//        userDetailsManager.createUser(
//                User.withUsername("member")
//                       .password(passwordEncoder.encode("1234"))
//                       .roles("MEMBER")
//                       .build()
//        );
//        userDetailsManager.createUser(
//                User.withUsername("admin")
//                       .password(passwordEncoder.encode("1234"))
//                       .roles("ADMIN")
//                       .build()
//        );
//
//        return userDetailsManager;
//    }

    @Bean
    CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }
    @Bean
    CustomLogoutHandler customLogoutHandler(){
        return new CustomLogoutHandler();
    }

    @Bean
    CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    @Bean
    Filter sessionRemoveFilter(){
        return new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
               HttpServletResponse resp = (HttpServletResponse) response;
               resp.setHeader("Set-Cookie", "JSESSIONID=; Path=/; Max-Age=0; HttpOnly" );

               chain.doFilter(request,response);
            }
        };

    }



}
