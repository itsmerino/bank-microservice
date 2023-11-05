package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WalletEntityToWalletConverter implements Converter<WalletEntity, Wallet> {

    @Override
    public Wallet convert(WalletEntity walletEntity) {
        return Wallet.builder()
                .id(walletEntity.getId())
                .userId(walletEntity.getUserId())
                .address(walletEntity.getAddress())
                .privateKey(walletEntity.getPrivateKey())
                .balance(walletEntity.getBalance())
                .build();
    }
}
