package com.example.demo.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="lend")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lend {     //N 대 M 관계 테이블 매핑하고 만들기
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //1 대 N 관계   user와 .      EAGER : 즉시로딩
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name="FK_LEND_USER",
    foreignKeyDefinition = "FOREIGN KEY(username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)  //지연로딩 LAZY
    @JoinColumn(name = "bookcode", foreignKey = @ForeignKey(name="FK_LEND_BOOK",
            foreignKeyDefinition = "FOREIGN KEY(bookcode) REFERENCES book(bookcode) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Book book;

    @Column(name="lenddate")
    private LocalDate lendDate;     //대여 날짜
    @Column(name="returndate")
    private LocalDate returnDate;   //반납날짜



    
    
}
