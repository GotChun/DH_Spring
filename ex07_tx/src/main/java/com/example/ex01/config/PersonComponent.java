package com.example.ex01.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PersonComponent {
	private String name;
	private int age;
	private String addr;
	
	PersonComponent(){
		this.name="박효신";
		this.age=50;
		this.addr = "인천";
	}
}
