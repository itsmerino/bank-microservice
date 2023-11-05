package com.itsmerino.bank.domain;

import java.util.UUID;

public class UserMother {

    public static User random() {
        return User.builder()
                .id(UUID.randomUUID())
                .username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .build();
    }
}
