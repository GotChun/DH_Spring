package com.example.ex01.domain.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dao.UserDaoImpl;
import com.example.ex01.domain.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {

	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	public boolean userRegistration(UserDto userDto) throws SQLException {
		
		return userDaoImpl.insert(userDto) > 0;
	}
	
}
