package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequest;
import com.itsmerino.bank.infrastructure.rest.dto.DepositFundsBody;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepositFundsBodyToDepositFundsRequestConverter implements Converter<DepositFundsBody, DepositFundsRequest> {

    @Override
    public DepositFundsRequest convert(DepositFundsBody depositFundsBody) {
        return DepositFundsRequest.builder()
                .walletId(depositFundsBody.getWalletId())
                .amount(depositFundsBody.getAmount())
                .build();
    }
}
