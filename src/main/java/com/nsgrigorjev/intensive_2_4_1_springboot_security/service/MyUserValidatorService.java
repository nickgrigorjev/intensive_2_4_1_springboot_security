package com.nsgrigorjev.intensive_2_4_1_springboot_security.service;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserValidatorService {
    private final UserRepository userRepository;

    public Optional<User> getUserFromDb(String username) {
        return userRepository.findByUsername(username);
    }
}
