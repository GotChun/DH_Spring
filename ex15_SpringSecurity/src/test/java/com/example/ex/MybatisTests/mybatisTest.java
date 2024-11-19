package com.example.ex.MybatisTests;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class mybatisTest {
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void t1() {
		userMapper.Insert(new UserDto("aaa","1234",null));
	}
	
	@Test
	public void t2() {
		UserDto userDto = userMapper.SelectOne("aaa");
		System.out.println(userDto);
	}
	
}
