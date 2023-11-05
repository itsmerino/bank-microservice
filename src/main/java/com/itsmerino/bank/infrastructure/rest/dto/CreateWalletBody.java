package com.itsmerino.bank.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
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
public class CreateWalletBody {

    @NotNull
    private UUID userId;

    @NotBlank
    private String address;

    @NotBlank
    private String privateKey;
}
