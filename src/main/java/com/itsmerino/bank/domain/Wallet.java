package com.itsmerino.bank.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Wallet {

    private UUID id;
    private UUID userId;
    private String address;
    private String privateKey;
    private Integer balance;
}
