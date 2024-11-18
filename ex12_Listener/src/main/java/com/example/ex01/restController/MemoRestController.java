package com.example.ex01.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/memo")
@Slf4j
public class MemoRestController {

	@Autowired
	private MemoServiceImpl memoServiceImpl;
	
	@GetMapping("/add_get")
	public ResponseEntity<String> add_get(MemoDto memoDto) {
		log.info("GET /memo/add_get"+memoDto);
		memoServiceImpl.memoAddTx(memoDto);
		return new ResponseEntity("insert_ok",HttpStatus.OK);
		
	}
	
	@PostMapping(value="/add_post",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void add_post(@RequestBody MemoDto memoDto) throws Exception{
		log.info("GET /memo/add_post"+memoDto);
		
		memoServiceImpl.memoAddTx(memoDto);
	}
	
	
	@GetMapping(value="/getMemo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<MemoDto>> getMemo() {
		log.info("GET /memo/getMemo.." );
		
		List<MemoDto> list = memoServiceImpl.getMemos();
		
		   return ResponseEntity.ok(list);
//		   return ResponseEntity(memoServiceImpl.getMemos(),HttpStatus.OK);    //  httpStatus OK 의 상태코드 , getMemos() 메서드의 결과를 감싸서 응답객체를 반환해준다.
		
	}
	
	@PutMapping(value="/put",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> update_put(@RequestBody MemoDto memoDto) {	//@RequestBody 로 실려 나온다.
		log.info("put mapping.. /memo/put"+memoDto);
		
		boolean isUpdate= memoServiceImpl.memoUpdate_patch(memoDto);   //유효성 검사를 위해 boolean 에 결과값 저장
		return isUpdate? new ResponseEntity(isUpdate,HttpStatus.OK) : new ResponseEntity(isUpdate,HttpStatus.BAD_REQUEST);
		
//		return new ResponseEntity("okokk!!",HttpStatus.OK); 				ResponseEntity<String>이라는 반환형에 해도 되긴 하던데 ?
	}
	
	@PatchMapping(value="/patch",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> update_patch(@RequestBody MemoDto memoDto) {
		log.info("/patch... patch !! "+memoDto);			//patch 는 부븐을 수정   put 은 전체 매핑
		boolean isUpdate = memoServiceImpl.memoUpdate_patch(memoDto);
		return isUpdate? new ResponseEntity(isUpdate,HttpStatus.OK) : new ResponseEntity(isUpdate,HttpStatus.BAD_REQUEST);					//결국 이 responseEntity 는 웹의 반응결과를 같이 함수결과와 보내주기 위한 객체 용도인듯하다.
		
		
	}
	
	@DeleteMapping("/delete")
	public void delete(int id) {
		log.info("/delete... Delete !! ");
		memoServiceImpl.memoDelete(id);
		
	}
	

	
}
