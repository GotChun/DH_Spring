package ex01_lombok_DI.mybatisTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dao.MemoDaoImpl;
import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.mapper.MemoMapper;
import com.example.ex01.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class myBatisTest {

	
	@Autowired
	MemoServiceImpl memoService;
	
	@Autowired
	MemoMapper memoMapper;
	
	@Test
	//Insert XML
	void test8() {
	memoMapper.insert_xml(new MemoDto(-1,"qwddqw","qwdqwd",null));
	
	}
	
	@Test
	void test11() {
		memoService.memoUpdate(new MemoDto(9,"대해박","대해박",null));
	}
	
	@Test
	void test111() throws Exception {
		memoService.memoRegistration(new MemoDto(-1,"dqwcz","gsfd",null));
	}
	
	
	@Test
	void test12() {
		//INSERT _ XML
		memoMapper.insert_xml(new MemoDto(-1,"MYBATIS_xml!!","xml@xml.com",null));
	}
	
	@Test
	void test() {
//		memoMapper.delete_xml(13);
//		MemoDto dto = memoMapper.select_xml(15);
		
//		List<MemoDto> list = memoMapper.SelectAll_xml_1();
//		list.forEach(System.out::println);
		List<Map<String,Object> > listMap = memoMapper.selectAll_xml_3();
		listMap.forEach(map->{
			for(String key : map.keySet())
				System.out.println(key + " " + map.get(key));
			
		});			
	}
	@Test
	void test3() {
		MemoDto dto = new MemoDto(-1,"key","이메일",null);	
		System.out.println(dto);
		memoMapper.Insert(dto);
		System.out.println(dto);
	}
	
	@Test
	void test5() {
		
		Map<String,Object> param = new HashMap();
		param.put("type", "text");
		param.put("keyword", "!!");
		//IF TEST
		List<Map<String,Object>> list =  memoMapper.select_if_xml(param);
		
		list.forEach(map->{
			for(String key : map.keySet())
				System.out.println(key + " " + map.get(key));
			
		});
	}
	@Test
	void test6() {
		
		Map<String,Object> param = new HashMap();
		param.put("type", "writer");
		param.put("keyword", "qw");
		//IF TEST
		List<Map<String,Object>> list =  memoMapper.select_when_xml(param);
		
		list.forEach(map->{
			for(String key : map.keySet())
				System.out.println(key + " " + map.get(key));
		});
	}
	
	@Test
	void test7() {
		
		Map<String,Object> param = new HashMap();
		param.put("type", "createAt");
		param.put("keyword", "2024-11-14");
		//IF TEST
		List<Map<String,Object>> list =  memoMapper.select_when_xml(param);
		
		list.forEach(map->{
			for(String key : map.keySet())
				System.out.println(key + " " + map.get(key));
		});
	}
	
	@Autowired
	private MemoDaoImpl memoDaoImpl;
	
	@Test
	void test10() {
		int result = memoDaoImpl.insert(new MemoDto(-1,"dqw","pdh",null));
		System.out.println(result);
	}
	
	
	
}
