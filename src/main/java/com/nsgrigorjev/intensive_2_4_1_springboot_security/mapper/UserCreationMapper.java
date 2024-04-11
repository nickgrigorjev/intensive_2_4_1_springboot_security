package com.nsgrigorjev.pp_2_4_1_springboot_security.mapper;

import com.nsgrigorjev.pp_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.pp_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.pp_2_4_1_springboot_security.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreationMapper implements Mapper<UserCreationDto, User> {

    private final PasswordEncoder passwordEncoder;

    public static UserResponseDto toDto(User entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getName(),
                entity.getLastname(),
                entity.getAge(),
                entity.getRoles());
    }

    public static User toEntity(UserCreationDto dto) {
        return new User(dto.getName(), dto.getLastname(), dto.getAge());
    }

    public static User toEntity(UserResponseDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getLastname(), dto.getAge());
    }

    @Override
    public User map(UserCreationDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreationDto object, User user) {
        user.setUsername(object.getUsername());
        user.setName(object.getName());
        user.setLastname(object.getLastname());
        user.setAge(object.getAge());
        user.setRoles(object.getRoles());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
        //TODO 08.04.2024 - 23:49: запилить вставку множества ролей
//        user.setRoles();
    }

    @Override
    public User map(UserCreationDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
