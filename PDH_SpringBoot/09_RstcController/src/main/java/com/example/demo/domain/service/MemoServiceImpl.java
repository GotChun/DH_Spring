package com.example.demo.domain.service;


import com.example.demo.domain.dao.MemoDaoImpl;
import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemoServiceImpl {

	@Autowired
	private MemoDaoImpl memoDaoImpl;
	
	@Autowired
	private MemoMapper memoMapper;
	
	public boolean memoRegistration(MemoDto memoDto) throws Exception {
		//메모 등록 처리
		return memoDaoImpl.insert(memoDto)>0;
	}
	
	
	public boolean memoRegistration_mapper(MemoDto memoDto) throws Exception {
		//메모 등록 처리
		return memoMapper.Insert(memoDto)>0;
	}
	
	
}
