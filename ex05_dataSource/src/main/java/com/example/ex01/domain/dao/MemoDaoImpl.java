package com.example.ex01.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ex01.domain.dto.MemoDto;


@Repository
public class MemoDaoImpl {

	
	@Autowired
	private DataSource dataSource3;
	
	public int insert(MemoDto memoDto) throws Exception {
		Connection conn;
		PreparedStatement pstmt;
		conn = dataSource3.getConnection();
		pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, memoDto.getText());
		pstmt.setString(2, memoDto.getWriter());
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	
}
