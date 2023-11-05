package com.itsmerino.bank.infrastructure.persistence.entity;

import java.util.UUID;

public class UserEntityMother {

    public static UserEntity random() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(UUID.randomUUID().toString())
                .build();
    }
}
