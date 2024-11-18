package com.example.ex01.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.ex01.handler.CustomHandler;
import com.example.ex01.interceptor.MemoInterceptor;
import com.example.ex01.listener.CustomContextRefreshedListener;
import com.example.ex01.listener.MemoAddEventListener;
import com.example.ex01.listener.RequestHandleEventListener;

@Configuration
@EnableWebMvc
@ComponentScans({
	@ComponentScan("com.example.ex01.controller"),
	@ComponentScan("com.example.ex01.restController"),
})
public class WebMVCConfig implements WebMvcConfigurer{		//파일 입출력을 할건데 그걸 원래 servlet-context.xml 파일에다가 뭐 정의하는데 여기서 하는거에 더 익숙해져야돼서 여기서 하는중

	@Bean
	public ViewResolver viewResolver() {			// 뷰 리졸버 직접 만들기
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1024*1024*10*2);				//전체 업로드 허용 사이즈 20MB
		multipartResolver.setMaxUploadSizePerFile(1024*1024*10*2);		//파일 1개당 허용가능한 업로드 20MB
		multipartResolver.setMaxInMemorySize(1024*1024*10*2);			// 캐시 공간
		
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new MemoInterceptor()).addPathPatterns("/memo/*");		//메모 url 요청이 들어왔을때 인터셉터가 동작	객체가 생성되면서 .
	}
	
	//handler 이벤트
	@Bean
	public RequestHandleEventListener requestHandleEventListener() {
		return new RequestHandleEventListener();
	}
	
	
	//리프레쉬 리스너
	@Bean
	public CustomContextRefreshedListener customContextRefreshedListener() {
		return new CustomContextRefreshedListener();
	}
	
	@Bean
	public MemoAddEventListener memoAddEventListener() {
		return new MemoAddEventListener();
	}
	
	//핸들러 매핑
	
	//1. 빈 매핑(BeanNameUrlHandelerMapping) : 요청 URL 을 동일한 이름을 가진 Bean 에 매핑 
	@Bean
	public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}
	
	
	@Bean(name="/custom_01")
	public CustomHandler customHandler() {
		return new CustomHandler();
	}
	//2)SimpleUrlHandlerMapping : 개발자가 직접 매핑정보를 설정 , 정적자원에 대한 매핑정보 설정이 기본값
	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		
		Map<String,Object> urlMap = new HashMap();
		urlMap.put("/custom_02", new CustomHandler());
		
		handlerMapping.setUrlMap(urlMap);
		return handlerMapping;
	}
	
	//3) RequestMappingHandlerMapping : Controller 와 매핑되는 URL 을 찾아내고 해당 URL 에 대한 요청 처리
	//적절한 컨트롤러 및 메서드를 찾아 매핑(@RequestMapping , @GetMapping , @PostMapping .. 등)
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() throws NoSuchMethodException, SecurityException {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		
		//URL 에 매핑될 메서드 찾기(Reflection이용)
		Method method = CustomHandler.class.getMethod("hello", null);
		
		//URL 요청 매핑정보 구성(요청 Method , URL ..)
		RequestMappingInfo mappingInfo = RequestMappingInfo.paths("/custom_03")
															.methods(RequestMethod.GET)
															.build();
		//요청 매핑정보 , 핸들러 , 메서드 등록						
		handlerMapping.registerMapping(mappingInfo, new CustomHandler(), method);
		return handlerMapping;
	}
	
	
	
	
	
}
