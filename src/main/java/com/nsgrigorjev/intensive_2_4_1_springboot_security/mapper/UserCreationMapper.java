package com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.Role;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreationMapper implements Mapper<UserCreationDto, User> {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Override
    public User map(UserCreationDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreationDto object, User user) {

        HashSet<Role> roles = new HashSet<>();
        Role role = roleService.findByRoleName("ROLE_USER");
        roles.add(role);

        user.setUsername(object.getUsername());
        user.setName(object.getName());
        user.setLastname(object.getLastname());
        user.setAge(object.getAge());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        user.setRoles(roles);
    }

    @Override
    public User map(UserCreationDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
