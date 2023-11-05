package com.itsmerino.bank.application.createwallet.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.WalletMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletToCreateWalletResponseConverterTest {

    private final WalletToCreateWalletResponseConverter sut = new WalletToCreateWalletResponseConverter();

    @Test
    void itShouldConvertWalletToCreateWalletResponse() {
        Wallet wallet = WalletMother.random();

        CreateWalletResponse createWalletResponse = sut.convert(wallet);

        assertNotNull(createWalletResponse);
        assertAll(
                () -> assertNotNull(createWalletResponse.getId()),
                () -> assertEquals(wallet.getUserId(), createWalletResponse.getUserId()),
                () -> assertEquals(wallet.getAddress(), createWalletResponse.getAddress())
        );
    }
}