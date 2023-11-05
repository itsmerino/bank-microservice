package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequest;
import com.itsmerino.bank.infrastructure.rest.dto.TransferFundsBody;
import com.itsmerino.bank.infrastructure.rest.dto.TransferFundsBodyMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferFundsBodyToTransferFundsRequestConverterTest {

    private final TransferFundsBodyToTransferFundsRequestConverter sut = new TransferFundsBodyToTransferFundsRequestConverter();

    @Test
    void itShouldConvertTransferFundsBodyToTransferFundsRequest() {
        TransferFundsBody transferFundsBody = TransferFundsBodyMother.random();

        TransferFundsRequest transferFundsRequest = sut.convert(transferFundsBody);

        assertNotNull(transferFundsRequest);
        assertAll(
                () -> assertEquals(transferFundsBody.getWalletFrom(), transferFundsRequest.getWalletFrom()),
                () -> assertEquals(transferFundsBody.getWalletTo(), transferFundsRequest.getWalletTo()),
                () -> assertEquals(transferFundsBody.getAmount(), transferFundsRequest.getAmount())
        );
    }
}