package com.example.demo.domain.service;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.mapper.MemoMapper;
import com.example.demo.domain.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Slf4j
public class TxTestServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemoMapper memoMapper;
    
    
        //MYBATIS EXCEPTION
        @Transactional(rollbackFor = SQLException.class,transactionManager = "dataSourceTransactionManager")  //SQL 예외가 발생하면 롤백
        void txMapper(){
            log.info("txMapper 시작 ㄱㄱㄱㄱ");
            MemoDto memoDto = new MemoDto(1234,"a","a");    //트랜잭션을 설정해주니 값이 안들어감
            memoMapper.Insert_tx(memoDto);
            memoMapper.Insert_tx(memoDto);
        }
    
    
    
        //JPA EXCEPTION
        @Transactional(rollbackFor = SQLException.class,transactionManager = "jpaTransactionManager")
        void jpaRepository() throws SQLException {
            log.info("jpa리포지토리 시작 ㄱㄱㄱㄱ");

            Book book = new Book(2555L,"a","a","2","a");
            bookRepository.save(book);
            throw new SQLException("SQL 오류임 ");

        }

    


}
