package com.example.ex01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrincipalDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub		//중계함수 ? 	//아이디 정보를 가지고 와서 DB로부터 있는지 없는지 확인한다s
		UserDto userDto = userMapper.SelectOne(username);
		if(userDto==null) 
			throw new UsernameNotFoundException("유저명을 찾을 수 없습니다 ! ");		//계정을 찾아서 매니저로 던져주는 역할을 하는 함수
		
		
		return new PrincipalDetails(userDto);
	}
	
	



	
	
	
}
