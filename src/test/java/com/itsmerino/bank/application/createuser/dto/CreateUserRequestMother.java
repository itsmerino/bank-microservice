package com.itsmerino.bank.application.createuser.dto;

import java.util.UUID;

public class CreateUserRequestMother {

    public static CreateUserRequest random() {
        return CreateUserRequest.builder()
                .username(UUID.randomUUID().toString())
                .build();
    }
}
