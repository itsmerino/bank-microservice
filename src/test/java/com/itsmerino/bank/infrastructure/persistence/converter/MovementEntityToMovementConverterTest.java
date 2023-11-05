package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntityMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementEntityToMovementConverterTest {

    private final MovementEntityToMovementConverter sut = new MovementEntityToMovementConverter();

    @Test
    void itShouldConvertMovementEntityToMovement() {
        MovementEntity movementEntity = MovementEntityMother.random();

        Movement movement = sut.convert(movementEntity);

        assertNotNull(movement);
        assertAll(
                () -> assertEquals(movementEntity.getId(), movement.getId()),
                () -> assertEquals(movementEntity.getType(), movement.getType()),
                () -> assertEquals(movementEntity.getWalletFrom(), movement.getWalletFrom()),
                () -> assertEquals(movementEntity.getWalletTo(), movement.getWalletTo()),
                () -> assertEquals(movementEntity.getAmount(), movement.getAmount()),
                () -> assertEquals(movementEntity.getDate(), movement.getDate())
        );
    }
}