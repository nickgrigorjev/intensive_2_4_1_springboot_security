package com.nsgrigorjev.intensive_2_4_1_springboot_security.service;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.Role;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository repository;

    @Transactional
    public Role findByRoleName(String roleName) {
        return repository.findRoleByRoleName(roleName);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }
}
