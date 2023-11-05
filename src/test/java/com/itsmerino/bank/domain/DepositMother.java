package com.itsmerino.bank.domain;

import java.util.Random;

public class DepositMother {

    public static Deposit random() {
        return Deposit.builder()
                .wallet(WalletMother.random())
                .amount(new Random().nextInt())
                .build();
    }
}
