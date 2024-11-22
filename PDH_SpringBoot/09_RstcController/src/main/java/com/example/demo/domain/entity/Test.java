package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    private Long id;
    private String col1;
    private String col2;


    @CollectionTable(name="Test_col3",      //1대 N 관계
                    joinColumns = @JoinColumn(name="id",        
                            foreignKey = @ForeignKey(name = "FK_TEST_COL3",  //외래키명
                                foreignKeyDefinition = "FOREIGN KEY(id) REFERENCES Test(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    )
    @ElementCollection
    List<String> col3;  //test 테이블의 id를 참조하고 있는 FK 값이 있는 테이블이 생긴다.


}
