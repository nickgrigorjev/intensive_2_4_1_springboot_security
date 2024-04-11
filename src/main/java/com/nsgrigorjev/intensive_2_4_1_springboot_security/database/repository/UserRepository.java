package com.nsgrigorjev.pp_2_4_1_springboot.database.repository;

import com.nsgrigorjev.pp_2_4_1_springboot.database.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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





}
