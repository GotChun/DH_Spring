package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.Lend;
import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        User user1 = userRepository.findById("123").get(); //get ?
        Book book1 = bookRepository.findByBookNameAndIsbn("hey","11-11");

        Lend lend = Lend.builder()
                .lendDate(null)
                .returnDate(null)
                .user(user1)
                .book(book1)
                .build();
        lendRepository.save(lend);
    }


    @Test
    public void t2(){
//        Lend lend = lendRepository.findById(1L).get();
//        System.out.println(lend.getId());
//        System.out.println(lend.getUser());

        List<Lend> lend = lendRepository.findLendsByUser("123");
        lend.forEach(l->{
            System.out.println(l.getUser());       //user 테이블이 나오는게 아니라 lend 테이블에 있는 username 이 나온다.
        });
    }

    @Test
    public void t3(){
        List<Lend> lends = lendRepository.findLendsByUserAndBook();
        lends.forEach(System.out::println);     //

    }


}