package com.itsmerino.bank.domain;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class MovementMother {

    public static Movement random() {
        return Movement.builder()
                .id(UUID.randomUUID())
                .type(new String[]{"DEPOSIT", "TRANSFER"}[new Random().nextInt(1)])
                .walletFrom(UUID.randomUUID())
                .walletTo(UUID.randomUUID())
                .amount(new Random().nextInt())
                .date(LocalDateTime.now())
                .build();
    }
}
