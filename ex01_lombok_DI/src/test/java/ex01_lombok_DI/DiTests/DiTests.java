package ex01_lombok_DI.DiTests;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dto.PersonDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DiTests {

	@Autowired
	PersonDto personDto1;
	@Autowired
	PersonDto personDto2;
	
	@Test
	void Di_test1() {
		System.out.println("wqdqwd");
		assertNotNull(personDto1);
		System.out.println(personDto1);
		System.out.println(personDto2);
	}
	
	
}
