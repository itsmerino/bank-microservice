package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntity;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntityMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletEntityToWalletConverterTest {

    private final WalletEntityToWalletConverter sut = new WalletEntityToWalletConverter();

    @Test
    void itShouldConvertWalletEntityToWallet() {
        WalletEntity walletEntity = WalletEntityMother.random();

        Wallet wallet = sut.convert(walletEntity);

        assertNotNull(wallet);
        assertAll(
                () -> assertEquals(walletEntity.getId(), wallet.getId()),
                () -> assertEquals(walletEntity.getUserId(), wallet.getUserId()),
                () -> assertEquals(walletEntity.getAddress(), wallet.getAddress()),
                () -> assertEquals(walletEntity.getPrivateKey(), wallet.getPrivateKey()),
                () -> assertEquals(walletEntity.getBalance(), wallet.getBalance())
        );
    }
}