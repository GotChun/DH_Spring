package com.example.demo.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DataSourceConfigTest {

//    @Autowired
//    private Datasource datasource2;
//
//
//
//    @Test
//    public void t1() throws SQLException {
//        System.out.println(datasource);
//
//        Connection conn = datasource.getConnection();
//        PreparedStatement pstmt =
//                conn.prepareStatement("insert into tbl_memo values(988,'aa','ww'now())");
//
//        pstmt.executeUpdate();
//    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void t2() {
        String beanName = "dataSource2";
        DataSource dataSource = applicationContext.getBean(beanName, DataSource.class);
        assertNotNull(dataSource);
    }

}