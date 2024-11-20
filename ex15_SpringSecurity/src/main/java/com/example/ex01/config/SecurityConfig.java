package com.example.ex01.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.ex01.config.auth.PrincipalDetailsServiceImpl;
import com.example.ex01.config.auth.exception.CustomAccessDeniedHandler;
import com.example.ex01.config.auth.exception.CustomAuthenticationEntryPoint;
import com.example.ex01.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.ex01.config.auth.loginHandler.CustomLoginSucccessHandler;
import com.example.ex01.config.auth.loginHandler.CustomLogoutHandler;
import com.example.ex01.config.auth.loginHandler.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailsServiceImpl principalDetailsServiceImpl;
	
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//권한체크
		http.authorizeRequests()
			.antMatchers("/","/join").permitAll()
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/member").hasRole("MEMBER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
		
		//로그인
		http.formLogin()	//로그인 설정을 구성하는 부분
			.loginPage("/login")	//기본 로그인 페이지를 지정
			.permitAll()			
			.successHandler(new CustomLoginSucccessHandler())	//로그인이 성공했을 때의 핸들러	  //이런건 직접 정의하는 객체인듯 ?
			.failureHandler(new CustomAuthenticationFailureHandler());	//로그인 실패시 처리
		
		
		
		//로그아웃
		http.logout()
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler())				//로그아웃시의 핸들러
			.logoutSuccessHandler(new CustomLogoutSuccessHandler());	//로그아웃 성공시의 핸들러
			
			
		
		//예외처리
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint())		//미인증 사용자 예외처리
			.accessDeniedHandler(new CustomAccessDeniedHandler());				//권한 실패(부족)시 예외처리 , 더 높은 권한으로 접근하려 할 때 예외처리
		
		
		//REMEMBER_ME
		http.rememberMe()
			.key("rememberMeKey") //사용자 키 값
			.rememberMeParameter("remember-me")	//파라미터 값	
			.alwaysRemember(false)  //항상 리멤버미 기능을 사용할지 여부
			.tokenValiditySeconds(60*60)  //리멤버미 기능 유지시간
			.tokenRepository(tokenRepository());	//토큰 저장소를 설정
	}


	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//			.withUser("user")
//			.password(passwordEncoder.encode("1234"))
//			.roles("USER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("member")
//			.password(passwordEncoder.encode("1234"))
//			.roles("MEMBER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password(passwordEncoder.encode("1234"))
//			.roles("ADMIN");
		
		auth.userDetailsService(principalDetailsServiceImpl).passwordEncoder(passwordEncoder);
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {		//이거는 무조건 회원비밀번호에만 사용하는 부분인가 ? 다른 암호화에는 사용못하나 ? 그러면 autowired 를 한번밖에 사용못하는데 그럼..
		return new BCryptPasswordEncoder();
	}		

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
		
	
}