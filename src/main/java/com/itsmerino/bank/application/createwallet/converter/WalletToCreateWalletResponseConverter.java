package com.itsmerino.bank.application.createwallet.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.domain.Wallet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WalletToCreateWalletResponseConverter implements Converter<Wallet, CreateWalletResponse> {

    @Override
    public CreateWalletResponse convert(Wallet wallet) {
        return CreateWalletResponse.builder()
                .id(wallet.getId())
                .userId(wallet.getUserId())
                .address(wallet.getAddress())
                .build();
    }
}
