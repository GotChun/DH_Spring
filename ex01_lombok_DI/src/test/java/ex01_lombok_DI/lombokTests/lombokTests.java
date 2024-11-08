package ex01_lombok_DI.lombokTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.ex01.domain.dto.PersonDto;

public class lombokTests {
	
	@Test
	void PersonDto_Test() {
		PersonDto dto1 = new PersonDto();
		dto1.setName("홍길동");
		dto1.setAddress("대구");
		dto1.setAge(50);
		System.out.println(dto1);
		
	}
	
	@Test
	void PersonDto_Test2() {
		PersonDto dto = PersonDto.builder()
						.address("창녕군")
						.age(999)
						.name("박대해")
						.build();
		System.out.println(dto);
		
		assertNotNull(dto); 	//null 이 아닌지 판단
	}
	
}