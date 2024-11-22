package com.example.demo.domain.dto;

import com.example.demo.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {      //userDto 를 만드는게 아니었다. UserEntity를 정의해서 사용하는 거였음. JPA

    private String username;
    private String password;
    private String role;

    public static User dtoToEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())    //dto를 엔티티로 바꿔줌
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();

    }

    public static UserDto entityToDto(User user){  //안해도 되는 작업이긴 하지만 매번 왔다갔다 해야되서 번거롭다고 한다.
    return UserDto.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .role(user.getRole())
            .build();
    }


}
