package com.itsmerino.bank.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Deposit {

    private Wallet wallet;
    private Integer amount;
}
