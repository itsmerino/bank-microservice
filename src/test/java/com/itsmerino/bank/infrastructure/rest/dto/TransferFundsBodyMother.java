package com.itsmerino.bank.infrastructure.rest.dto;

import java.util.Random;
import java.util.UUID;

public class TransferFundsBodyMother {

    public static TransferFundsBody random(){
        return TransferFundsBody.builder()
                .walletFrom(UUID.randomUUID())
                .walletTo(UUID.randomUUID())
                .amount(new Random().nextInt())
                .build();
    }
}
