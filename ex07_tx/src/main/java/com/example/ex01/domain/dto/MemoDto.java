package com.example.ex01.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {
	
	@Min(value=10,message= "최소 숫자는 10이상 이어야합니다.")
	@Max(value=1000,message="최대 숫자 1000임")
	@NotNull(message="비어있음 현재")
	private Integer id;		//
	@NotBlank(message="메모 입력하셈")
	private String text;
	
	@Email(message="이메일 입력하셈")
	@NotBlank(message="작성자입력하셈")
	private String writer;
	
	@NotNull(message="날짜정보 입력하셈")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")  //포매팅 작업이 안되면 바인더가 인식을 못한다.  형식을 제대로 인식하지 못해 오류가 발생한다.
	private LocalDateTime createAt;  
	
//	private LocalDate dateTest;
	
	
}
