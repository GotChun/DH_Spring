package com.example.ex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ex01.domain.dto.PersonDto;

@Configuration
public class PersonDtoBeanConfig {

	@Bean
	public PersonDto person03() {
		return PersonDto.builder()
				.name("다리우스")
				.age(111)
				.addr("?")
				.build();
	}
	
	@Bean(name="personBean")
	public PersonDto person04() {
		return PersonDto.builder()
				.name("김범수")
				.age(55)
				.addr("서울")
				.build();
	}
	
}
