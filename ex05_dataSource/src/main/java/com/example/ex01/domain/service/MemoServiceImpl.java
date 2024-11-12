package com.example.ex01.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dao.MemoDaoImpl;
import com.example.ex01.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MemoServiceImpl {

	@Autowired
	private MemoDaoImpl memoDaoImpl;
	
	
	
	public boolean memoRegistration(MemoDto memoDto) throws Exception {
		//메모 등록 처리
		return memoDaoImpl.insert(memoDto)>0;
	}
	
	
}
