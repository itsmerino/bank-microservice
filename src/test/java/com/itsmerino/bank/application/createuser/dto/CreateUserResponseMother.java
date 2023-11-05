package com.itsmerino.bank.application.createuser.dto;

import java.util.UUID;

public class CreateUserResponseMother {

    public static CreateUserResponse random() {
        return CreateUserResponse.builder()
                .id(UUID.randomUUID())
                .username(UUID.randomUUID().toString())
                .build();
    }
}
