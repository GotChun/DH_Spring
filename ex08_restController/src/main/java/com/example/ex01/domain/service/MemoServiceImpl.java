package com.example.ex01.domain.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor =  SQLException.class)
	public boolean memoUpdate(MemoDto memoDto) {		//업데이트시는 특정 열만 업데이트한다 해도 db에서 가져올 때는 모든 열 정보를 다 가져온다음 하는게 좋다고 함. 혹시 모를 상황에 대비할 수도 있고 
		int result = memoDaoImpl.update(memoDto);
		
		return result>0;
	}
	
	//메모 수정 하기
	@Transactional(rollbackFor =  SQLException.class)
	public boolean memoUpdate_patch(MemoDto memoDto) {		//업데이트시는 특정 열만 업데이트한다 해도 db에서 가져올 때는 모든 열 정보를 다 가져온다음 하는게 좋다고 함. 혹시 모를 상황에 대비할 수도 있고 
		int result = memoDaoImpl.update_patch(memoDto);
		
		return result>0;
	}
	
	//메모 삭제
	@Transactional(rollbackFor =  SQLException.class)
	public boolean memoDelete(int id) {
		boolean isDelete = memoDaoImpl.delete(id)>0;
		return isDelete;
	}
	
	//메모 불러오기 (단건)
	@Transactional(rollbackFor =  SQLException.class)
	public MemoDto getMemo(int id) {
		memoMapper.select(0);
		return null;
	}
	
	
	//모든 메모 가져오기
	@Transactional(rollbackFor =  SQLException.class)
	public List<MemoDto> getMemos(){
	
		
		return memoMapper.selectAll();
	}
	
	public boolean memoAdd(MemoDto memoDto) throws Exception {
		//메모 등록 처리
		log.info("insert 전 before " + memoDto);
		int result = memoDaoImpl.insertTx(memoDto);
		log.info("insert 후 After" + memoDto);
		result = memoDaoImpl.insertTx(memoDto);
		return result>0;
		
	}
	
	@Transactional(rollbackFor =  SQLException.class)
	public boolean memoAddTx(MemoDto memoDto) {
		
		int result = memoDaoImpl.insertTx(memoDto);
		return result>0;
	}
	
	
	
	
}
