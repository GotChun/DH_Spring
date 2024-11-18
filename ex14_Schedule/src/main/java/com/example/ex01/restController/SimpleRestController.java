package com.example.ex01.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex01.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest")
@Slf4j
public class SimpleRestController {
	
	@GetMapping(value="/getText",produces = MediaType.TEXT_PLAIN_VALUE)
	public String t1() {
		log.info("getText");
		
		return "Helloworld";
	}
	

	@GetMapping(value="/getJson",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MemoDto t2() {
		log.info("getText");
		
		return new MemoDto(20,"memo","ex@ex.com",null);
	}
	
	@GetMapping(value="/getXml",produces = MediaType.APPLICATION_XML_VALUE)
	public MemoDto t3() {
		log.info("getXml");
		
		return new MemoDto(20,"memo","ex@ex.com",null);
	}
	
	
	@GetMapping(value="/getXmlList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> t4() {
		log.info("getXml..List...asdasd");
		
		List<MemoDto> list = new ArrayList();
		for(int i=1;i<=40;i++) {
			list.add(new MemoDto(i,"memo"+i,"exam"+i,null));
		}
		return list;
	}
	
	@GetMapping(value="/getJsonList/{start}/{end}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> t5(
				@PathVariable int start,
				@PathVariable int end
				
			) {
		log.info("getXml..List...asdasd");
		
		List<MemoDto> list = new ArrayList();
		for(int i=start;i<=end ;i++) {
			list.add(new MemoDto(i,"memo"+i,"exam"+i,null));
		}
		return list;
	}
	
	@GetMapping(value="/getJsonEntity/{show}",produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<MemoDto> t6(
				@PathVariable boolean show
			) {
		log.info("getjsonlist ffkfkfkffk");
		if(!show)
			return new ResponseEntity(null,HttpStatus.BAD_GATEWAY);
		
		return new ResponseEntity(new MemoDto(20,"memo","ex@ex.com",null),HttpStatus.OK);
	}
	
}
