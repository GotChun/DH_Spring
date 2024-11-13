package com.example.ex01.domain.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ex01.domain.dto.MemoDto;


@Repository
public class MemoDaoImpl {

//	
//	@Autowired
//	private DataSource dataSource3;
//	
//	public int insert(MemoDto memoDto) throws Exception {
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = dataSource3.getConnection();
//		pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
//		pstmt.setString(1, memoDto.getText());
//		pstmt.setString(2, memoDto.getWriter());
//		int result = pstmt.executeUpdate();
//		
//		return result;
//	}
//	
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.example.ex01.domain.mapper.MemoMapper.";
	
	public int insert(MemoDto memoDto) {
		sqlSession.insert(namespace + "Insert",memoDto);
		return memoDto.getId();
	}
	
}
