package ex01_lombok_DI.DataSourceTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= "file:src/main/webapp/WEB-INF/spring/root-context.xml")
class DSTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	void test() throws Exception {
		assertNotNull(dataSource);
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "메모TEST");
		pstmt.setString(2, "EXAMPLE@example.com");
		pstmt.executeUpdate();
		
	}
	
	@Autowired
	private DataSource dataSource2;

	
	@Test
	void test2() throws Exception {
		assertNotNull(dataSource);
		
		Connection conn = dataSource2.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "스고이");
		pstmt.setString(2, "아리가또고자이마스");
		pstmt.executeUpdate();
		
	}
	
	@Autowired
	private DataSource dataSource3;

	
	@Test
	void test3() throws Exception {
		assertNotNull(dataSource);
		
		Connection conn = dataSource2.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "스고이");
		pstmt.setString(2, "반갑습니다");
		pstmt.executeUpdate();
		
	}
	
	
}
