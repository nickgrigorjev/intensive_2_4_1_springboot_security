package com.nsgrigorjev.intensive_2_4_1_springboot_security.dto;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.Role;
import lombok.Value;

import java.util.Set;

@Value
public class UserEditDto {
    String username;
    String name;
    String lastname;
    Byte age;
    Set<Role> roles;
}
