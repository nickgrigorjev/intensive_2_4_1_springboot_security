package com.nsgrigorjev.intensive_2_4_1_springboot_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Value
public class UserCreationDto {

    @Email(message = "Логин должен иметь формат адреса электронной почты")
    @NotEmpty(message = "Логин не может быть пустым")
    String username;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "символов в имени должно быть от 2 до 128")
    String name;

    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 150, message = "символов в фамилии должно быть от 2 до 128")
    String lastname;

    @Min(value = 0, message = "Возраст должен быть больше 0")
    Byte age;

    @NotEmpty(message = "пароль не может быть пустым")
    @Size(min = 2, max = 128, message = "символов в пароле должно быть от 2 до 128")
    String rawPassword;

}
