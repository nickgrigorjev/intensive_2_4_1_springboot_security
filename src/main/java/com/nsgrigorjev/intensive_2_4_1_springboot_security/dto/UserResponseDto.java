package com.nsgrigorjev.pp_2_4_1_springboot.dto;

public record UserResponseDto(Long id,
                              String name,
                              String lastname,
                              byte age) {
}
