package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovementToMovementEntityConverter implements Converter<Movement, MovementEntity> {

    @Override
    public MovementEntity convert(Movement movement) {
        return MovementEntity.builder()
                .type(movement.getType())
                .walletFrom(movement.getWalletFrom())
                .walletTo(movement.getWalletTo())
                .amount(movement.getAmount())
                .date(movement.getDate())
                .build();
    }
}
