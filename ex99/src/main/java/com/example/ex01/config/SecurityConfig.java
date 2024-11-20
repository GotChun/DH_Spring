package com.example.ex01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity	//커스텀 설정을 하게 만들겠다는 어노테이션
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailServiceImpl principal;
	
	@Autowired
	private PasswordEncoder encorder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub	//인증 설정
		super.configure(auth);
		auth.userDetailsService(principalDetailsServiceImpl)
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub		//인터넷 설정	거의 모든 시큐리티의 설정
		
		//권한체크
		http.authorizeRequests()
			.antMatchers("/","/join").permitAll()
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/member").hasRole("MEMBER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
			
		http.formLogin()
			.loginPage("/login")
			.permitAll();
		
		http.logout()
			.permitAll();
		
		http.exceptionHandling();
			
		
		http.rememberMe();
	}

	
	
}
