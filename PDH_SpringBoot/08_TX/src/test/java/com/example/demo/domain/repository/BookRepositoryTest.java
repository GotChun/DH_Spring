package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        Book book  = Book.builder()
                .bookCode(1L)
                .bookName("book1UPDATE")
                .author("author1uodate")
                .publisher("publisher1")
                .isbn("11-1dsfjupdate")
                .build();

        Book result = bookRepository.save(book);    //save 함수 : 기본 DB 를 저장하거나 업데이트하는 함수
        assertThat(result).isEqualTo(book);
    }

    @Test
    public void t2(){
        Book book  = Book.builder()
                .bookCode(1L)
                .bookName("book1UPDATE")
                .author("author1uodate")
                .publisher("publisher1")
                .isbn("11-1dsfjupdate")
                .build();

        Book result = bookRepository.save(book);        //북코드가 일치하면 알아서 수정해준다
        assertThat(result).isEqualTo(book);
    }

    @Test
    public void t3(){
        Book book  = Book.builder()
                .bookCode(1L)
                .bookName("book1UPDATE")
                .author("author1uodate")
                .publisher("publisher1")
                .isbn("11-1dsfjupdate")
                .build();

        bookRepository.deleteById(1L);

    }

    @Test
    public void t4(){
        Optional<Book> bookOptional=bookRepository.findById(2L);
        if(bookOptional.isPresent()){
            System.out.println(bookOptional.get());
        }
    }

    @Test
    public void t5(){
        List<Book> list  = bookRepository.findAll();    //모든 요소 검색
        list.forEach(System.out::println);
    }


    @Test
    public void t6(){
        List<Book> list = bookRepository.findByBookName("게이바");    //특정 책 이름을 검색
        list.forEach(System.out::println);
    }

    @Test
    public void t7(){
        List<Book> list = bookRepository.findByPublisher("publisher1");
        list.forEach(System.out::println);
    }

    @Test
    public void t8(){       //조건절 문자열 확인
        List<Book> list = bookRepository.findByBookNameContains("U");
        list.forEach((System.out::println));
    }



}