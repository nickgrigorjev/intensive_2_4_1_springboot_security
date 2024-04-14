package com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findRoleByRoleName(String roleName);

    @Query(value = "SELECT r FROM Role r")
    List<Role> findAll();

    Set<Role> findByRoleNameIn(List<String> roleName);
}
