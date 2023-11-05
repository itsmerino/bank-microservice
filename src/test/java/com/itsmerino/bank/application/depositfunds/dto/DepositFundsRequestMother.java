package com.itsmerino.bank.application.depositfunds.dto;

import java.util.Random;
import java.util.UUID;

public class DepositFundsRequestMother {

    public static DepositFundsRequest random() {
        return DepositFundsRequest.builder()
                .walletId(UUID.randomUUID())
                .amount(new Random().nextInt())
                .build();
    }
}
