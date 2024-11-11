package ex01_lombok_DI.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ex01.controller.ParameterTestController;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Slf4j
public class controllerTests {

	@Autowired
	private ParameterTestController parameterTestController;
	
	private MockMvc mockmvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@BeforeEach				//사전에 정의한 것들을 실행   , JUnit 테스트 시 필요한 어노테이션
	public void setup() {
		mockmvc = MockMvcBuilders.webAppContextSetup(wac).build();		//목업 객체 생성
	}
	
	
	@Test
	public void t1() throws Exception {
		
		//GET / param  01
		mockmvc.perform(get("/param/p01").param("name", "홍길동"))
			.andExpect(status().isOk())
			.andDo(print());
		
		
	}
	
	@Test
	public void t2() throws Exception {
		
		//GET / param  01
		mockmvc.perform(post("/param/p03").param("name","홍길동") )
			.andExpect(status().isOk())
			.andDo(print());
		
		
	}
	
	
}
