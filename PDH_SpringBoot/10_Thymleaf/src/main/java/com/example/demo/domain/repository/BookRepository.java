package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Long> {
    //Book : 테이블과 매핑되는 객체
    //Long : 테이블의 기본 키 자료형
    List<Book> findByBookName(String bookName); //findBy 앞에 컬럼명 , 여기서는 앞글자가 대문자로 와야 한다고함.

    List<Book> findByPublisher(String publisher);

    //조건절 포함문자열(%)
    List<Book> findByBookNameContains(String keyword);

    Book findByBookNameAndIsbn(String bookName,String isbn);

    //행의 개수 받기
    int countByBookName(String bookname);

    //
    int countByBookNameContaining(String keyword);

    //책 이름으로 삭제
    void deleteByBookName(String bookname);
}
