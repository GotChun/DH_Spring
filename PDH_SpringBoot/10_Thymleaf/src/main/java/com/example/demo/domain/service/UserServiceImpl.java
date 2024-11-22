package com.example.demo.domain.service;

import com.example.demo.domain.dao.UserDaoImpl;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserDaoImpl userDaoImpl;

    public boolean memberJoin(UserDto userDto) throws SQLException {

        User user = UserDto.dtoToEntity(userDto);
        userRepository.save(user);
        return true;  //insert 성공시 true, 실패시 false 반환




//        User user = User.builder()
//                        .username(userDto.getUsername())
//                                .password(userDto.getPassword())  내가 한 방법.
//                                        .role(userDto.getRole())
//                                                .build();
//        User result = userRepository.save(user);
//        return result.equals(0);    //equal to 를해서 정수를 반환받기 때문에 boolean으로 비교가 가능


    }
}
