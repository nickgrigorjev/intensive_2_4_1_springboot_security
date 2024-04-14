package com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEditMapper implements Mapper<UserEditDto, User> {


    @Override
    public User map(UserEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserEditDto object, User user) {

        user.setUsername(object.getUsername());
        user.setName(object.getName());
        user.setLastname(object.getLastname());
        user.setAge(object.getAge());
        user.setRoles(object.getRoles());
    }

    @Override
    public User map(UserEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
