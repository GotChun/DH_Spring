package com.example.ex01.listener;

import org.springframework.context.ApplicationEvent;

import com.example.ex01.domain.dto.MemoDto;

public class MemoAddEvent extends ApplicationEvent{

	private MemoDto memoDto;
	public MemoAddEvent(Object source,MemoDto memoDto) {
		super(source);   //이벤트 처리관련 소스
		this.memoDto = memoDto;
	}
	@Override
	public String toString() {
		return "MemoAddEvent [memoDto=" + memoDto + "]";
	}

	
	
}
