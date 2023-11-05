package com.itsmerino.bank.infrastructure.rest.dto;

import java.util.UUID;

public class CreateUserBodyMother {

    public static CreateUserBody random() {
        return CreateUserBody.builder()
                .username(UUID.randomUUID().toString())
                .build();
    }
}
