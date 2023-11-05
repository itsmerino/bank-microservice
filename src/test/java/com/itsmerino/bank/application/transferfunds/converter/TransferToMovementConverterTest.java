package com.itsmerino.bank.application.transferfunds.converter;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.Transfer;
import com.itsmerino.bank.domain.TransferMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferToMovementConverterTest {

    private final TransferToMovementConverter sut = new TransferToMovementConverter();

    @Test
    void itShouldConvertTransferToMovementConverter() {
        Transfer transfer = TransferMother.random();

        Movement movement = sut.convert(transfer);

        assertNotNull(movement);
        assertAll(
                () -> assertEquals("TRANSFER", movement.getType()),
                () -> assertEquals(transfer.getWalletFrom().getId(), movement.getWalletFrom()),
                () -> assertEquals(transfer.getWalletTo().getId(), movement.getWalletTo()),
                () -> assertEquals(transfer.getAmount(), movement.getAmount()),
                () -> assertNotNull(movement.getDate())
        );
    }
}