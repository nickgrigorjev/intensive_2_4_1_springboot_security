package com.nsgrigorjev.intensive_2_4_1_springboot_security.util;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper.UserCreationMapper;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.MyUserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final MyUserValidatorService userValidatorService;
    private final UserCreationMapper userCreationMapper;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = userCreationMapper.map((UserCreationDto) target);

        userValidatorService.getUserFromDb(user.getUsername())
                .ifPresent(mayBeUser -> errors
                        .rejectValue(
                                "username",
                                "",
                                "Человек с таким именем пользователя существует"));
    }
}
