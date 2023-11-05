package com.itsmerino.bank.application.depositfunds.converter;

import com.itsmerino.bank.domain.Deposit;
import com.itsmerino.bank.domain.Movement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DepositToMovementConverter implements Converter<Deposit, Movement> {

    private static final String DEPOSIT = "DEPOSIT";

    @Override
    public Movement convert(Deposit deposit) {
        return Movement.builder()
                .type(DEPOSIT)
                .walletTo(deposit.getWallet().getId())
                .amount(deposit.getAmount())
                .date(LocalDateTime.now())
                .build();
    }
}
