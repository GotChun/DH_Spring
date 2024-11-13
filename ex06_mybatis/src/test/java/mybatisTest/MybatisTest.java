package mybatisTest;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.mapper.MemoMapper;
import com.example.ex01.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class MybatisTest {
	
//	@Autowired
//	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	MemoServiceImpl memoService;
//	@Test
//	void test1() {
//		log.info(sqlSessionFactory.toString());
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		Connection conn = sqlSession.getConnection();
//		System.out.println();
//		log.info(conn.toString());
//		System.out.println();
//		
//		System.out.println(conn.toString());
//		System.out.println();
//	}
//	
	@Autowired
//	SqlSessionTemplate sqlSessionTemplate;
//	
//	@Test
//	void test2() {
//		
//		Connection conn =  sqlSessionTemplate.getConnection();
//		System.out.println(sqlSessionTemplate);
//		System.out.println(conn);
//	}
//	
//	@Autowired
	MemoMapper memoMapper;
	
//	@Test
//	void test3() {
//		memoMapper.Insert(new MemoDto(-1,"스고이","박대해@ㅊㅊ.com",null));
//	}
//	
//	@Test
//	void test4() {  //업데이트
//		memoMapper.update(new MemoDto(7,"박대해","박대해",null));
//	}
//	
//	
//	
//	@Test		//삭제
//	void test5() {
//		memoMapper.delete(6);
//	}
	
	
//	@Test
//	void test6() {
//		List<MemoDto> dto =  memoMapper.selectAll();
//		
//		System.out.println(dto);
//		System.out.println();
//		dto.forEach(System.out::println);
//	}
	
//	
//	@Test
//	void test7() {
//		List<Map<String,Object>> dto =  memoMapper.selectAllByResultMap();
//		dto.forEach(map->{
//			
//			for(String key : map.keySet()) {
//				System.out.println(key + " : " + map.get(key));
//			}
//			
//		});
		
		
//	}
	
	
	@Test
	//Insert XML
	void test8() {
	memoMapper.insert_xml(new MemoDto(-1,"qwddqw","qwdqwd",null));
	
	}
	
	void test11() {
		memoService.mem
	}
	
}
