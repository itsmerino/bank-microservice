package com.itsmerino.bank.infrastructure.rest.dto;

import java.util.Random;
import java.util.UUID;

public class DepositFundsBodyMother {

    public static DepositFundsBody random(){
        return DepositFundsBody.builder()
                .walletId(UUID.randomUUID())
                .amount(new Random().nextInt())
                .build();
    }
}
