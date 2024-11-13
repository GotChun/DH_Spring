package com.example.ex01.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dao.MemoDaoImpl;
import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;


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
	
	//메모 수정 하기
	public boolean memoUpdate(MemoDto memoDto) {		//업데이트시는 특정 열만 업데이트한다 해도 db에서 가져올 때는 모든 열 정보를 다 가져온다음 하는게 좋다고 함. 혹시 모를 상황에 대비할 수도 있고 
		int result = memoMapper.update(memoDto);
		
		return result>0;
	}
	
	//메모 삭제
	public boolean memoDelete(int id) {
		int result = memoMapper.delete(id);
		return false;
	}
	
	//메모 불러오기 (단건)
	public MemoDto getMemo(int id) {
		memoMapper.select(0);
		return null;
	}
	
	
	//모든 메모 가져오기
	public List<MemoDto> getMemos(){
	
		return null;
	}
	
	
	
	
	
}
