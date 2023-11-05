package com.itsmerino.bank.application.depositfunds.converter;

import com.itsmerino.bank.domain.Deposit;
import com.itsmerino.bank.domain.DepositMother;
import com.itsmerino.bank.domain.Movement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositToMovementConverterTest {

    private final DepositToMovementConverter sut = new DepositToMovementConverter();

    @Test
    void itShouldConvertDepositToMovement() {
        Deposit deposit = DepositMother.random();

        Movement movement = sut.convert(deposit);

        assertNotNull(movement);
        assertAll(
                () -> assertEquals("DEPOSIT", movement.getType()),
                () -> assertNull(movement.getWalletFrom()),
                () -> assertEquals(deposit.getWallet().getId(), movement.getWalletTo()),
                () -> assertEquals(deposit.getAmount(), movement.getAmount()),
                () -> assertNotNull(movement.getDate())
        );
    }
}