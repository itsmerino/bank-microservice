package com.itsmerino.bank.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transfer {

    private Wallet walletFrom;
    private Wallet walletTo;
    private Integer amount;
}
