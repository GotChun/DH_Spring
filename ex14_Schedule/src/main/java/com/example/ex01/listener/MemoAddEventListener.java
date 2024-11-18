package com.example.ex01.listener;

import org.springframework.context.ApplicationListener;

public class MemoAddEventListener implements ApplicationListener<MemoAddEvent>{

	@Override
	public void onApplicationEvent(MemoAddEvent event) {
		System.out.println("[EVNET] MEMO ADD..." + event);
	}

	
	
}
