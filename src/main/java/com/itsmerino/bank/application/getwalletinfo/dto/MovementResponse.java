package com.itsmerino.bank.application.getwalletinfo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class MovementResponse {

    private UUID id;
    private String type;
    private UUID walletFrom;
    private UUID walletTo;
    private Integer amount;
    private LocalDateTime date;
}
