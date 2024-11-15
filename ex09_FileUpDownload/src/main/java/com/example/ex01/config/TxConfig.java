package com.example.ex01.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TxConfig {

	@Autowired
	private DataSource dataSource3;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		
		return new DataSourceTransactionManager(dataSource3);
	}
	
	
}
