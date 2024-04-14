package com.nsgrigorjev.intensive_2_4_1_springboot_security.service;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository.UserRepository;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Optional.ofNullable(userRepository.findByUsername(username)).isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new MyUserDetails(userRepository.findByUsername(username).orElseThrow());
        }
    }
}
