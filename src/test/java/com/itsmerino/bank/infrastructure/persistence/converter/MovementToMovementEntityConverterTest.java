package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.MovementMother;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementToMovementEntityConverterTest {

    private final MovementToMovementEntityConverter sut = new MovementToMovementEntityConverter();

    @Test
    void itShouldConvertMovementToMovementEntity() {
        Movement movement = MovementMother.random();

        MovementEntity movementEntity = sut.convert(movement);

        assertNotNull(movementEntity);
        assertAll(
                () -> assertEquals(movement.getType(), movementEntity.getType()),
                () -> assertEquals(movement.getWalletFrom(), movementEntity.getWalletFrom()),
                () -> assertEquals(movement.getWalletTo(), movementEntity.getWalletTo()),
                () -> assertEquals(movement.getAmount(), movementEntity.getAmount()),
                () -> assertEquals(movement.getDate(), movementEntity.getDate())
        );
    }
}