package com.example.ex01.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;
																	//요 부분 중요
public class RequestHandleEventListener implements ApplicationListener<RequestHandledEvent>{

	@Override
	public void onApplicationEvent(RequestHandledEvent event) {
		// TODO Auto-generated method stub
		System.out.println("RequestHandleEventListener's onApplicationEvent()" + event.getSource());
		
	}

}
