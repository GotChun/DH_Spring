package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class TxConfig {

	@Autowired
	private DataSource dataSource;

	@Bean(name="dataSourceTransactionManager")
	public DataSourceTransactionManager transactionManager() { //JDBC 기반 트랜잭션 관리를 지원하는 트랜잭션 매니저
		return new DataSourceTransactionManager(dataSource);  //단순한 JDBC 를 사용하여 쿼리를 실행할 때 사용한다.
	}

	// JPA TransactionManager Settings
	@Bean(name="jpaTransactionManager") //JPA 기반 트랜잭션 관리를 지원하는 트랜잭션 매니저
	public JpaTransactionManager jpaTransasctionManager(EntityManagerFactory entityManagerFactory) { //EntityManagerFactory 와 dataSource를 사용하여 트랜잭션을 관리함
		JpaTransactionManager transactionManager = new JpaTransactionManager(); //hibernate 같은 JPA 구현체를 사용하는 프로그램에서 사용됨
		transactionManager.setEntityManagerFactory(entityManagerFactory); //
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}


}