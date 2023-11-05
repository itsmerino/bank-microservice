package com.itsmerino.bank.application.depositfunds;

import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequest;
import com.itsmerino.bank.domain.Deposit;
import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.DepositPort;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class DepositFundsUseCase {

    private final ConversionService conversionService;
    private final DepositPort depositPort;
    private final MovementPort movementPort;
    private final WalletPort walletPort;

    public DepositFundsUseCase(ConversionService conversionService,
                               DepositPort depositPort,
                               MovementPort movementPort,
                               WalletPort walletPort) {
        this.conversionService = conversionService;
        this.depositPort = depositPort;
        this.movementPort = movementPort;
        this.walletPort = walletPort;
    }

    public void handle(DepositFundsRequest depositFundsRequest) {
        Wallet wallet = walletPort.getWallet(depositFundsRequest.getWalletId())
                .orElseThrow(WalletNotFoundException::new);
        Deposit deposit = mapDepositFundsRequestToDeposit(depositFundsRequest, wallet);

        depositFunds(deposit);
        createDepositMovement(deposit);
    }

    private Deposit mapDepositFundsRequestToDeposit(DepositFundsRequest depositFundsRequest,
                                                    Wallet wallet) {
        return Deposit.builder()
                .wallet(wallet)
                .amount(depositFundsRequest.getAmount())
                .build();
    }

    private void depositFunds(Deposit deposit) {
        depositPort.depositFunds(deposit);
    }

    private void createDepositMovement(Deposit deposit) {
        Movement movement = conversionService.convert(deposit, Movement.class);
        movementPort.createMovement(movement);
    }
}
