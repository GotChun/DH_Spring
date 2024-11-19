package com.example.ex01.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.ex01.domain.dto.UserDto;

@Mapper
public interface UserMapper {
	
	@Insert("insert into tbl_user values(#{username},#{password},'ROLE_USER' )")
	public void Insert(UserDto userDto);
		
	
	
	
	@Select("select * from tbl_user where username=#{username}")
	public UserDto SelectOne(String username);
	
}
