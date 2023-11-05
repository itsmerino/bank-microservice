package com.itsmerino.bank.application.createwallet.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.domain.Wallet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletRequestToWalletConverter implements Converter<CreateWalletRequest, Wallet> {

    @Override
    public Wallet convert(CreateWalletRequest createWalletRequest) {
        return Wallet.builder()
                .userId(createWalletRequest.getUserId())
                .address(createWalletRequest.getAddress())
                .privateKey(createWalletRequest.getPrivateKey())
                .build();
    }
}
