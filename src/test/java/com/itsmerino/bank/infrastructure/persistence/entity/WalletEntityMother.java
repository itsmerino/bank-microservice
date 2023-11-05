package com.itsmerino.bank.infrastructure.persistence.entity;

import java.util.Random;
import java.util.UUID;

public class WalletEntityMother {

    public static WalletEntity random() {
        return WalletEntity.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .address(UUID.randomUUID().toString())
                .privateKey(UUID.randomUUID().toString())
                .balance(new Random().nextInt())
                .build();
    }
}
