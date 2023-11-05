package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequest;
import com.itsmerino.bank.infrastructure.rest.dto.TransferFundsBody;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransferFundsBodyToTransferFundsRequestConverter implements Converter<TransferFundsBody, TransferFundsRequest> {

    @Override
    public TransferFundsRequest convert(TransferFundsBody transferFundsBody) {
        return TransferFundsRequest.builder()
                .walletFrom(transferFundsBody.getWalletFrom())
                .walletTo(transferFundsBody.getWalletTo())
                .amount(transferFundsBody.getAmount())
                .build();
    }
}
