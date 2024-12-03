package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");  //이게 class.forName 이랑 같은거
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookdb"); //mysql 에 있는 스키마 설정
        dataSource.setUsername("root");
        dataSource.setPassword("1234");   //비번 아이디 설정

        dataSource.setMaximumPoolSize(10);  //커넥션풀 사이즈 . 디폴트가 10개

        return dataSource;
    }
}
