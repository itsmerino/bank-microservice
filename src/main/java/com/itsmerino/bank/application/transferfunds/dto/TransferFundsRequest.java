package com.itsmerino.bank.application.transferfunds.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TransferFundsRequest {

    private UUID walletFrom;
    private UUID walletTo;
    private Integer amount;
}
