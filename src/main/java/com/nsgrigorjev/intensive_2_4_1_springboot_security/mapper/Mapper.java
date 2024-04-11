package com.nsgrigorjev.pp_2_4_1_springboot_security.mapper;

public interface Mapper<F, T> {
    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
