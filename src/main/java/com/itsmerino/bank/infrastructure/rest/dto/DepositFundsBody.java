package com.itsmerino.bank.infrastructure.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositFundsBody {

    @NotNull
    private UUID walletId;

    @NotNull
    @Min(1)
    private Integer amount;
}
