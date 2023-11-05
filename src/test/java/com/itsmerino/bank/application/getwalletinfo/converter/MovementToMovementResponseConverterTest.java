package com.itsmerino.bank.application.getwalletinfo.converter;

import com.itsmerino.bank.application.getwalletinfo.dto.MovementResponse;
import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.MovementMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementToMovementResponseConverterTest {

    private final MovementToMovementResponseConverter sut = new MovementToMovementResponseConverter();

    @Test
    void itShouldMovementToMovementResponseConverter() {
        Movement movement = MovementMother.random();

        MovementResponse movementResponse = sut.convert(movement);

        assertNotNull(movementResponse);
        assertAll(
                () -> assertEquals(movement.getId(), movementResponse.getId()),
                () -> assertEquals(movement.getType(), movementResponse.getType()),
                () -> assertEquals(movement.getWalletFrom(), movementResponse.getWalletFrom()),
                () -> assertEquals(movement.getWalletTo(), movementResponse.getWalletTo()),
                () -> assertEquals(movement.getAmount(), movementResponse.getAmount()),
                () -> assertNotNull(movementResponse.getDate())
        );
    }
}