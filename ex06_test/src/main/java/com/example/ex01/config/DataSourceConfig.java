package com.example.ex01.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {		//데이터 커넥션 자동연결 설정파일
	@Autowired
	private DataSource dataSource2;		//함수명이 자동으로 등록됨
	
	@Bean
	public DataSource dataSource2() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		BasicDataSource dataSource = new BasicDataSource();		//커넥션 pooling 처리
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		dataSource.setMaxTotal(10);  //최대 연결 개수
		dataSource.setMaxIdle(8); //최대 유휴 연결개수
		dataSource.setInitialSize(5);   //초기 연결 개수
		dataSource.setMinIdle(3); //최소 유휴 연결 개수
		dataSource.setMaxWaitMillis(30000); //CP에서 Connection 객체를 가져오기 위해 기다리는 최대시간
		
		return dataSource;
	}

	
	@Autowired
	private DataSource dataSource3;		//함수명이 자동으로 등록됨
	
	@Bean
	public DataSource dataSource3() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		HikariDataSource dataSource = new HikariDataSource();		//커넥션 pooling 처리
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		dataSource.setMaximumPoolSize(100);
		
		return dataSource;
	}
	
	
}
