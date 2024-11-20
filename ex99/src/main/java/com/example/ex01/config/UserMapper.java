package com.example.ex01.config;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	@Insert("insert into tbl_user values(${username},#{password},#{role} )")
	public void Insert(UserDto userDto);
	
	@Select("select * from tbl_user where username=#{username}")
	public UserDto selectOne(String username);
	
	
}

	
