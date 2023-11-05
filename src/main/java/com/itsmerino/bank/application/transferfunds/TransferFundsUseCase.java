package com.itsmerino.bank.application.transferfunds;

import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequest;
import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.Transfer;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.exception.CanNotTransferToSameWalletException;
import com.itsmerino.bank.domain.exception.InsufficientFundsException;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.TransferPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class TransferFundsUseCase {

    private final ConversionService conversionService;
    private final MovementPort movementPort;
    private final TransferPort transferPort;
    private final WalletPort walletPort;

    public TransferFundsUseCase(ConversionService conversionService,
                                MovementPort movementPort,
                                TransferPort transferPort,
                                WalletPort walletPort) {
        this.conversionService = conversionService;
        this.movementPort = movementPort;
        this.transferPort = transferPort;
        this.walletPort = walletPort;
    }

    public void handle(TransferFundsRequest transferFundsRequest) {
        Wallet walletFrom = walletPort.getWallet(transferFundsRequest.getWalletFrom())
                .orElseThrow(WalletNotFoundException::new);
        Wallet walletTo = walletPort.getWallet(transferFundsRequest.getWalletTo())
                .orElseThrow(WalletNotFoundException::new);

        validateAreDifferentWallets(walletFrom, walletTo);

        Transfer transfer = mapTransferFundsRequestToTransfer(walletFrom, walletTo, transferFundsRequest);

        transferFunds(transfer);
        createTransferMovement(transfer);
    }

    private void validateAreDifferentWallets(Wallet walletFrom,
                                             Wallet walletTo) {
        boolean isSameWallet = walletFrom.getAddress().equals(walletTo.getAddress());
        if (isSameWallet) {
            throw new CanNotTransferToSameWalletException();
        }
    }

    private Transfer mapTransferFundsRequestToTransfer(Wallet walletFrom,
                                                       Wallet walletTo,
                                                       TransferFundsRequest transferFundsRequest) {
        return Transfer.builder()
                .walletFrom(walletFrom)
                .walletTo(walletTo)
                .amount(transferFundsRequest.getAmount())
                .build();
    }

    private void transferFunds(Transfer transfer) {
        try {
            transferPort.transferFunds(transfer);
        } catch (Exception e) {
            throw new InsufficientFundsException();
        }
    }

    private void createTransferMovement(Transfer transfer) {
        Movement movement = conversionService.convert(transfer, Movement.class);
        movementPort.createMovement(movement);
    }
}
