package com.itsmerino.bank.application.transferfunds.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.Transfer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransferToMovementConverter implements Converter<Transfer, Movement> {

    private static final String TRANSFER = "TRANSFER";

    @Override
    public Movement convert(Transfer transfer) {
        return Movement.builder()
                .type(TRANSFER)
                .walletFrom(transfer.getWalletFrom().getId())
                .walletTo(transfer.getWalletTo().getId())
                .amount(transfer.getAmount())
                .date(LocalDateTime.now())
                .build();
    }
}
