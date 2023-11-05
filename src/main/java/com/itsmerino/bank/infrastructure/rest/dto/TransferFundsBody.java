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
public class TransferFundsBody {

    @NotNull
    private UUID walletFrom;

    @NotNull
    private UUID walletTo;

    @NotNull
    @Min(1)
    private Integer amount;
}
