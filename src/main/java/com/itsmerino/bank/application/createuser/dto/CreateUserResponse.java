package com.itsmerino.bank.application.createuser.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateUserResponse {

    private UUID id;
    private String username;
}
