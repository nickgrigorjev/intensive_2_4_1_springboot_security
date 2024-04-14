package com.nsgrigorjev.intensive_2_4_1_springboot_security.database.repository;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    <S extends User> S save(S user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findByUsername(String username);


}
