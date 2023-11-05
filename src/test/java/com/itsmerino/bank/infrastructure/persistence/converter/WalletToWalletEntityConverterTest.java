package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.WalletMother;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletToWalletEntityConverterTest {

    private final WalletToWalletEntityConverter sut = new WalletToWalletEntityConverter();

    @Test
    void itShouldConvertWalletToWalletEntity() {
        Wallet wallet = WalletMother.random();

        WalletEntity walletEntity = sut.convert(wallet);

        assertNotNull(walletEntity);
        assertAll(
                () -> assertEquals(wallet.getUserId(), walletEntity.getUserId()),
                () -> assertEquals(wallet.getAddress(), walletEntity.getAddress()),
                () -> assertEquals(wallet.getPrivateKey(), walletEntity.getPrivateKey())
        );
    }
}