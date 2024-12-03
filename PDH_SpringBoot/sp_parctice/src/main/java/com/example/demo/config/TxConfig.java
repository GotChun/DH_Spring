package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

public class TxConfig {

    @Autowired
    private DataSource dataSource;


    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();  //엔티티를 생성할 때 트랜잭션 처리를 한다는듯?
        transactionManager.setEntityManagerFactory(entityManagerFactory); //트랜 할
        transactionManager.setDataSource(dataSource); // 트랜잭션 처리할 데이터소스
        return transactionManager;
    }
}
