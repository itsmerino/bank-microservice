package com.itsmerino.bank.application.createwallet.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequestMother;
import com.itsmerino.bank.domain.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateWalletRequestToWalletConverterTest {

    private final CreateWalletRequestToWalletConverter sut = new CreateWalletRequestToWalletConverter();

    @Test
    void itShouldConvertCreateWalletRequestToWallet() {
        CreateWalletRequest createWalletRequest = CreateWalletRequestMother.random();

        Wallet wallet = sut.convert(createWalletRequest);

        assertNotNull(wallet);
        assertAll(
                () -> assertEquals(createWalletRequest.getUserId(), wallet.getUserId()),
                () -> assertEquals(createWalletRequest.getAddress(), wallet.getAddress()),
                () -> assertEquals(createWalletRequest.getPrivateKey(), wallet.getPrivateKey())
        );
    }
}