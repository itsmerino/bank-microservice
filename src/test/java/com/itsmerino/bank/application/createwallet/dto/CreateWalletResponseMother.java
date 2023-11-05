package com.itsmerino.bank.application.createwallet.dto;

import java.util.UUID;

public class CreateWalletResponseMother {

    public static CreateWalletResponse random() {
        return CreateWalletResponse.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .address(UUID.randomUUID().toString())
                .build();
    }
}
