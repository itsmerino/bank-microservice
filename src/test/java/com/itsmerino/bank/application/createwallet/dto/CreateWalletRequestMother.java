package com.itsmerino.bank.application.createwallet.dto;

import java.util.UUID;

public class CreateWalletRequestMother {

    public static CreateWalletRequest random() {
        return CreateWalletRequest.builder()
                .userId(UUID.randomUUID())
                .address(UUID.randomUUID().toString())
                .privateKey(UUID.randomUUID().toString())
                .build();
    }
}
