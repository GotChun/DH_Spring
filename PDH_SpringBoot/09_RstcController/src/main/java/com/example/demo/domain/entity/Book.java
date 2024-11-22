package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {     //테이블이 알아서 생김   //이걸 토대로 테이블을 만들어낸다.
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Auto Increment 를 사용한다는 어노테이션
    @Column(name = "bookcode")
    private Long bookCode;  //나중에 쿼리를 사용할 때를 대비해서 카멜표기법 안쓰는게 좋다고 함 안그럼 book_name 이렇게 들어간다고함
   // db column name
   @Column(name = "bookname" , length = 1024)
    private String bookName;
    @Column(name="publisher")
    private String publisher;
    @Column(name="author")
    private String author;
    @Column(name="isbn")
    private String isbn;

}
