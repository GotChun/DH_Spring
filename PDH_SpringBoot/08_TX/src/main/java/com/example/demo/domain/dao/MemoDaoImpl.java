package com.example.demo.domain.dao;

import com.example.demo.domain.dto.MemoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




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

	private static String namespace = "com.example.demo.domain.mapper.MemoMapper.";

	public int insert(MemoDto memoDto) {
		sqlSession.insert(namespace + "Insert",memoDto);
		return memoDto.getId();
	}

}
