package com.itsmerino.bank.domain;

import java.util.Random;

public class TransferMother {

    public static Transfer random() {
        return Transfer.builder()
                .walletFrom(WalletMother.random())
                .walletTo(WalletMother.random())
                .amount(new Random().nextInt())
                .build();
    }
}
