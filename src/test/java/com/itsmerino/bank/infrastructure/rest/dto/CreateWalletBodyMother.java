package com.itsmerino.bank.infrastructure.rest.dto;

import java.util.UUID;

public class CreateWalletBodyMother {

    public static CreateWalletBody random(){
        return CreateWalletBody.builder()
                .userId(UUID.randomUUID())
                .address(UUID.randomUUID().toString())
                .privateKey(UUID.randomUUID().toString())
                .build();
    }
}
