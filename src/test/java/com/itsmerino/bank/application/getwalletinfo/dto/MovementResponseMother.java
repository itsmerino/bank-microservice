package com.itsmerino.bank.application.getwalletinfo.dto;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class MovementResponseMother {

   public static MovementResponse random() {
       return MovementResponse.builder()
               .id(UUID.randomUUID())
               .type(new String[]{"DEPOSIT", "TRANSFER"}[new Random().nextInt(1)])
               .walletFrom(UUID.randomUUID())
               .walletTo(UUID.randomUUID())
               .amount(new Random().nextInt())
               .date(LocalDateTime.now())
               .build();
   }
}
