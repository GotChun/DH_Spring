package com.example.ex01.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	
	@NotNull(message="아이디없음")
	private String userid;
	@NotNull(message="비번치셈")
	private String password;
	@NotNull(message="다시비번치셈")
	private String rePassword;
	@NotNull(message="유저이름 입력하셈")
	private String username;
	@NotNull(message="폰번호 입력하셈")
	private String phone;
	@NotNull(message="집코드 ? 입력하셈")
	private String zipcode;
	@NotNull(message="주소 1 입력")
	private String addr1;
	@NotNull(message="주소 2 입력")
	private String addr2;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") 
	private LocalDate birthday;
}
