package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WalletToWalletEntityConverter implements Converter<Wallet, WalletEntity> {

    @Override
    public WalletEntity convert(Wallet wallet) {
        return WalletEntity.builder()
                .userId(wallet.getUserId())
                .address(wallet.getAddress())
                .privateKey(wallet.getPrivateKey())
                .build();
    }
}
