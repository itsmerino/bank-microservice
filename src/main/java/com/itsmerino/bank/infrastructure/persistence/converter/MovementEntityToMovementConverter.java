package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovementEntityToMovementConverter implements Converter<MovementEntity, Movement> {

    @Override
    public Movement convert(MovementEntity movementEntity) {
        return Movement.builder()
                .id(movementEntity.getId())
                .type(movementEntity.getType())
                .walletFrom(movementEntity.getWalletFrom())
                .walletTo(movementEntity.getWalletTo())
                .amount(movementEntity.getAmount())
                .date(movementEntity.getDate())
                .build();
    }
}
