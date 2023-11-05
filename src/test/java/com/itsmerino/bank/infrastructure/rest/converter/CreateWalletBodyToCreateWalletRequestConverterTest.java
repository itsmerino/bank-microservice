package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.infrastructure.rest.dto.CreateWalletBody;
import com.itsmerino.bank.infrastructure.rest.dto.CreateWalletBodyMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateWalletBodyToCreateWalletRequestConverterTest {

    private final CreateWalletBodyToCreateWalletRequestConverter sut = new CreateWalletBodyToCreateWalletRequestConverter();

    @Test
    void itShouldConvertCreateWalletBodyToCreateWalletRequest() {
        CreateWalletBody createWalletBody = CreateWalletBodyMother.random();

        CreateWalletRequest createWalletRequest = sut.convert(createWalletBody);

        assertNotNull(createWalletRequest);
        assertAll(
                () -> assertEquals(createWalletBody.getUserId(), createWalletRequest.getUserId()),
                () -> assertEquals(createWalletBody.getAddress(), createWalletRequest.getAddress()),
                () -> assertEquals(createWalletBody.getPrivateKey(), createWalletRequest.getPrivateKey())
        );
    }
}