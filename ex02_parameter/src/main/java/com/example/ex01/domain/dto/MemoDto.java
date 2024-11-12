package com.example.ex01.domain.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {

	private int id;		//정수형인데 문자입력하니까 당연히 안나오지 ㅅㅂ 
	private String text;
	private String writer;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")  //포매팅 작업이 안되면 바인더가 인식을 못한다.
	private LocalDateTime createAt;  
	
}
