package com.itsmerino.bank.application.createwallet.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateWalletResponse {

    private UUID id;
    private UUID userId;
    private String address;
}
