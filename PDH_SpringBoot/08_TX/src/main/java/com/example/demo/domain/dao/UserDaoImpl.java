package com.example.demo.domain.dao;

import com.example.demo.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@Repository
public class UserDaoImpl {

	Connection conn;
	PreparedStatement pstmt;
	
	@Autowired
	private DataSource dataSource;
	
	public int insert(UserDto userDto) throws SQLException {
	conn = dataSource.getConnection();
	pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?)");
	pstmt.setString(1, userDto.getUsername());
	pstmt.setString(2, userDto.getPassword());
	pstmt.setString(3, userDto.getRole());
	int result = pstmt.executeUpdate();
	
	pstmt.close();
	
	return result;
	}
	
	
	
}
