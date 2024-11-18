package com.example.ex01.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class CustomContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{//컨텍스트가 리프레쉬 될때 이벤트

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("리프레쉬 될 때 이벤트 발생 : " + event.getSource());//
	} 

}
