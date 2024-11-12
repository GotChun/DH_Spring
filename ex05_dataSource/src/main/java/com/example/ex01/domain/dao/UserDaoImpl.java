package com.example.ex01.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ex01.domain.dto.UserDto;

@Repository
public class UserDaoImpl {

	Connection conn;
	PreparedStatement pstmt;
	
	@Autowired
	private DataSource dataSource3;
	
	public int insert(UserDto userDto) throws SQLException {
	conn = dataSource3.getConnection();
	pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?,?,?,?,?,?,?,?)");
	pstmt.setString(1, userDto.getUserid());
	pstmt.setString(2, userDto.getPassword());
	pstmt.setString(3, userDto.getRePassword());
	pstmt.setString(4, userDto.getUsername());
	pstmt.setString(5, userDto.getPhone());
	pstmt.setString(6, userDto.getZipcode());
	pstmt.setString(7, userDto.getAddr1());
	pstmt.setString(8, userDto.getAddr2());
	pstmt.setDate(9, userDto.getBirthday());
	int result = pstmt.executeUpdate();
	
	
	return result;
	}
	
	
	
}
