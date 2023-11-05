package com.itsmerino.bank.application.getwalletinfo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WalletInfoResponse {

    private Integer balance;
    private List<MovementResponse> movements;
}
