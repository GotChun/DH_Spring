package com.example.ex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.ex01.interceptor.MemoInterceptor;

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
	
	

	
	
}
