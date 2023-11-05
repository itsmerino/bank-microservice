package com.itsmerino.bank.application.depositfunds;

import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequest;
import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequestMother;
import com.itsmerino.bank.domain.*;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.DepositPort;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DepositFundsUseCaseTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final DepositPort depositPort = mock(DepositPort.class);
    private final MovementPort movementPort = mock(MovementPort.class);
    private final WalletPort walletPort = mock(WalletPort.class);
    private final DepositFundsUseCase sut = new DepositFundsUseCase(conversionService, depositPort, movementPort, walletPort);

    @Test
    void itShouldDepositFunds() {
        Wallet wallet = WalletMother.random();
        Movement movement = MovementMother.random();
        DepositFundsRequest depositFundsRequest = DepositFundsRequestMother.random();

        when(walletPort.getWallet(depositFundsRequest.getWalletId())).thenReturn(Optional.of(wallet));
        when(conversionService.convert(any(Deposit.class), eq(Movement.class))).thenReturn(movement);

        sut.handle(depositFundsRequest);

        verify(walletPort).getWallet(depositFundsRequest.getWalletId());
        verify(depositPort).depositFunds(any(Deposit.class));
        verify(conversionService).convert(any(Deposit.class), eq(Movement.class));
        verify(movementPort).createMovement(movement);
    }

    @Test
    void itShouldThrowWalletNotFoundException() {
        DepositFundsRequest depositFundsRequest = DepositFundsRequestMother.random();

        when(walletPort.getWallet(depositFundsRequest.getWalletId())).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> sut.handle(depositFundsRequest));
    }
}