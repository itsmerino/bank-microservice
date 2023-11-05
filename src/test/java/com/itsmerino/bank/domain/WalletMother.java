package com.itsmerino.bank.domain;

import java.util.Random;
import java.util.UUID;

public class WalletMother {

    public static Wallet random() {
        return Wallet.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .address(UUID.randomUUID().toString())
                .privateKey(UUID.randomUUID().toString())
                .balance(new Random().nextInt())
                .build();
    }
}
