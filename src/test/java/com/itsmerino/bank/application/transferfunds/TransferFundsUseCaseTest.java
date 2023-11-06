package com.itsmerino.bank.application.transferfunds;

import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequest;
import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequestMother;
import com.itsmerino.bank.domain.*;
import com.itsmerino.bank.domain.exception.CanNotTransferToSameWalletException;
import com.itsmerino.bank.domain.exception.InsufficientFundsException;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.TransferPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TransferFundsUseCaseTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final MovementPort movementPort = mock(MovementPort.class);
    private final TransferPort transferPort = mock(TransferPort.class);
    private final WalletPort walletPort = mock(WalletPort.class);
    private final TransferFundsUseCase sut = new TransferFundsUseCase(conversionService, movementPort, transferPort, walletPort);

    @Test
    void itShouldTransferFunds() {
        Wallet walletFrom = WalletMother.random();
        Wallet walletTo = WalletMother.random();
        Movement movement = MovementMother.random();
        TransferFundsRequest transferFundsRequest = TransferFundsRequestMother.random();

        when(walletPort.getWallet(transferFundsRequest.getWalletFrom())).thenReturn(Optional.of(walletFrom));
        when(walletPort.getWallet(transferFundsRequest.getWalletTo())).thenReturn(Optional.of(walletTo));
        when(conversionService.convert(any(Transfer.class), eq(Movement.class))).thenReturn(movement);

        sut.handle(transferFundsRequest);

        verify(walletPort).getWallet(transferFundsRequest.getWalletFrom());
        verify(walletPort).getWallet(transferFundsRequest.getWalletTo());
        verify(transferPort).transferFunds(any(Transfer.class));
        verify(conversionService).convert(any(Transfer.class), eq(Movement.class));
        verify(movementPort).createMovement(movement);
    }

    @Test
    void itShouldThrowWalletNotFoundExceptionWhenWalletFromNotFound() {
        TransferFundsRequest transferFundsRequest = TransferFundsRequestMother.random();

        when(walletPort.getWallet(transferFundsRequest.getWalletFrom())).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> sut.handle(transferFundsRequest));
    }

    @Test
    void itShouldThrowWalletNotFoundExceptionWhenWalletToNotFound() {
        TransferFundsRequest transferFundsRequest = TransferFundsRequestMother.random();

        when(walletPort.getWallet(transferFundsRequest.getWalletTo())).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> sut.handle(transferFundsRequest));
    }

    @Test
    void itShouldThrowCanNotTransferToSameWalletException() {
        Wallet wallet = WalletMother.random();
        TransferFundsRequest transferFundsRequest = TransferFundsRequestMother.withWallets(wallet, wallet);

        when(walletPort.getWallet(transferFundsRequest.getWalletFrom())).thenReturn(Optional.of(wallet));
        when(walletPort.getWallet(transferFundsRequest.getWalletTo())).thenReturn(Optional.of(wallet));

        assertThrows(CanNotTransferToSameWalletException.class, () -> sut.handle(transferFundsRequest));
    }

    @Test
    void itShouldThrowInsufficientFundsException() {
        Wallet walletFrom = WalletMother.random();
        Wallet walletTo = WalletMother.random();
        TransferFundsRequest transferFundsRequest = TransferFundsRequestMother.random();

        when(walletPort.getWallet(transferFundsRequest.getWalletFrom())).thenReturn(Optional.of(walletFrom));
        when(walletPort.getWallet(transferFundsRequest.getWalletTo())).thenReturn(Optional.of(walletTo));
        doThrow(RuntimeException.class).when(transferPort).transferFunds(any(Transfer.class));

        assertThrows(InsufficientFundsException.class, () -> sut.handle(transferFundsRequest));
    }
}