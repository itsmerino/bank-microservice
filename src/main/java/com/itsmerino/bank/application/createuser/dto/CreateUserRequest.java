package com.itsmerino.bank.application.createuser.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

    private String username;
}
