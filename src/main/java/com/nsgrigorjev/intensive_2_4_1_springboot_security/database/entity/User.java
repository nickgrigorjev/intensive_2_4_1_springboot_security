package com.nsgrigorjev.pp_2_4_1_springboot.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max = 100, message = "name should be between 2 and 100")
    private String name;


    @NotEmpty(message = "lastname should not be empty")
    @Size(min = 2, max = 150, message = "name should be between 2 and 150")
    @Column(name = "last_name")
    private String lastname;

    @Min(value = 0, message = "should be more than 0")
    @Column(name = "age")
    private byte age;

    public User(String name, String lastName, byte age) {
        this.name = name;
        this.lastname = lastName;
        this.age = age;
    }
}
