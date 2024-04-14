package com.nsgrigorjev.intensive_2_4_1_springboot_security.service;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository.RoleRepository;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository.UserRepository;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserResponseDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper.UserCreationMapper;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserCreationMapper userCreationMapper;
    private final UserResponseMapper userResponseMapper;

    public UserResponseDto findById(Long id) {
        return userRepository.findById(id).map(userResponseMapper::map).orElseThrow();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Transactional
    public UserResponseDto persist(UserCreationDto userDto) {
        return Optional.ofNullable(userDto).map(userCreationMapper::map).map(userRepository::save).map(userResponseMapper::map).orElseThrow();
    }

    @Transactional
    public UserResponseDto update(UserResponseDto userDto, List<String> rolesFromView) {
        Optional<User> mayBeUserFromDb = userRepository.findById(userDto.getId());
        User updateUser = new User();
        updateUser.setId(userDto.getId());
        updateUser.setUsername(userDto.getUsername());
        updateUser.setName(userDto.getName());
        updateUser.setLastname(userDto.getLastname());
        updateUser.setAge(userDto.getAge());
        updateUser.setPassword(mayBeUserFromDb.orElseThrow().getPassword());
        if (rolesFromView == null) {
            updateUser.setRoles(Collections.singleton(roleRepository.findRoleByRoleName("ROLE_USER")));
        } else {
            updateUser.setRoles(roleRepository.findByRoleNameIn(rolesFromView));
        }
        userRepository.save(updateUser);
        return userResponseMapper.map(updateUser);

    }


}
