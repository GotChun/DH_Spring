package com.example.demo.domain.repository;

import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        User user = new User("testUser", "testPassword", "testEmail");
        userRepository.save(user);
    }

    @Test
    public void t1(){

        List<User> list = userRepository.selectByRole("ROLE_USER");
        list.forEach(System.out::println);

    }

    @Test
    public void t2(){
        List<User> list = userRepository.selectByRoleAndPassword("ROLE_USER","123");
        list.forEach(System.out::println);

    }

    @Test
    public void t3(){
        List<User> list = userRepository.selectByPassword("123");
        list.forEach(System.out::println);
    }


}
