package com.example.ex01.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@Data			//게터세터 자동으로 합쳐서 만듬 	
@Builder		//객체생성 디자인패턴
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	
	private String name;
	private int age;
	private String address;
	
}