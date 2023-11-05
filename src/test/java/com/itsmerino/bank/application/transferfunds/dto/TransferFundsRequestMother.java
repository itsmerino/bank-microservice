package com.itsmerino.bank.application.transferfunds.dto;

import com.itsmerino.bank.domain.Wallet;

import java.util.Random;
import java.util.UUID;

public class TransferFundsRequestMother {

    public static TransferFundsRequest random() {
        return TransferFundsRequest.builder()
                .walletFrom(UUID.randomUUID())
                .walletTo(UUID.randomUUID())
                .amount(new Random().nextInt())
                .build();
    }

    public static TransferFundsRequest withWallets(Wallet walletFrom,
                                                   Wallet walletTo) {
        return TransferFundsRequest.builder()
                .walletFrom(walletFrom.getId())
                .walletTo(walletTo.getId())
                .amount(new Random().nextInt())
                .build();
    }
}
