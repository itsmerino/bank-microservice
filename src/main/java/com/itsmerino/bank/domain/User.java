package com.itsmerino.bank.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class User {

    private UUID id;
    private String username;
    private String password;
}
