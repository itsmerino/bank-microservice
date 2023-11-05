package com.itsmerino.bank.application.getwalletinfo.converter;

import com.itsmerino.bank.application.getwalletinfo.dto.MovementResponse;
import com.itsmerino.bank.domain.Movement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovementToMovementResponseConverter implements Converter<Movement, MovementResponse> {

    @Override
    public MovementResponse convert(Movement movement) {
        return MovementResponse.builder()
                .id(movement.getId())
                .type(movement.getType())
                .walletFrom(movement.getWalletFrom())
                .walletTo(movement.getWalletTo())
                .amount(movement.getAmount())
                .date(movement.getDate())
                .build();
    }
}
