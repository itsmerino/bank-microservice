package com.itsmerino.bank.application.depositfunds.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DepositFundsRequest {

    private UUID walletId;
    private Integer amount;
}
