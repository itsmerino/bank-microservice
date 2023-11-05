package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.infrastructure.rest.dto.CreateWalletBody;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletBodyToCreateWalletRequestConverter implements Converter<CreateWalletBody, CreateWalletRequest> {

    @Override
    public CreateWalletRequest convert(CreateWalletBody createWalletBody) {
        return CreateWalletRequest.builder()
                .userId(createWalletBody.getUserId())
                .address(createWalletBody.getAddress())
                .privateKey(createWalletBody.getPrivateKey())
                .build();
    }
}
