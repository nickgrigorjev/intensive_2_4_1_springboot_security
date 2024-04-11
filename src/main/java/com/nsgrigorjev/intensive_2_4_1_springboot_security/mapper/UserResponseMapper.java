package com.nsgrigorjev.pp_2_4_1_springboot_security.mapper;

import com.nsgrigorjev.pp_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.pp_2_4_1_springboot_security.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements Mapper<User, UserResponseDto>{
    @Override
    public UserResponseDto map(User object) {
        return new UserResponseDto(
                object.getId(),
                object.getUsername(),
                object.getName(),
                object.getLastname(),
                object.getAge(),
                object.getRoles()
        );
    }

}
