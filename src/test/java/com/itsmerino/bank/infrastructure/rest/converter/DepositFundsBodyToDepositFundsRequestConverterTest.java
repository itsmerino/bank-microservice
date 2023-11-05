package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequest;
import com.itsmerino.bank.infrastructure.rest.dto.DepositFundsBody;
import com.itsmerino.bank.infrastructure.rest.dto.DepositFundsBodyMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositFundsBodyToDepositFundsRequestConverterTest {

    private final DepositFundsBodyToDepositFundsRequestConverter sut = new DepositFundsBodyToDepositFundsRequestConverter();

    @Test
    void itShouldConvertDepositFundsBodyToDepositFundsRequest() {
        DepositFundsBody depositFundsBody = DepositFundsBodyMother.random();

        DepositFundsRequest depositFundsRequest = sut.convert(depositFundsBody);

        assertNotNull(depositFundsRequest);
        assertAll(
                () -> assertEquals(depositFundsBody.getWalletId(), depositFundsRequest.getWalletId()),
                () -> assertEquals(depositFundsBody.getAmount(), depositFundsRequest.getAmount())
        );
    }
}