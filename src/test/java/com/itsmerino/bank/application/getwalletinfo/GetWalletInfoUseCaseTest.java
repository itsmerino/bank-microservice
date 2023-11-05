package com.itsmerino.bank.application.getwalletinfo;

import com.itsmerino.bank.application.getwalletinfo.dto.MovementResponse;
import com.itsmerino.bank.application.getwalletinfo.dto.MovementResponseMother;
import com.itsmerino.bank.application.getwalletinfo.dto.WalletInfoResponse;
import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.MovementMother;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.WalletMother;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetWalletInfoUseCaseTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final MovementPort movementPort = mock(MovementPort.class);
    private final WalletPort walletPort = mock(WalletPort.class);
    private final GetWalletInfoUseCase sut = new GetWalletInfoUseCase(conversionService, movementPort, walletPort);

    @Test
    void itShouldDepositFunds() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = WalletMother.random();
        Movement movement = MovementMother.random();
        MovementResponse movementResponse = MovementResponseMother.random();

        when(walletPort.getWallet(walletId)).thenReturn(Optional.of(wallet));
        when(movementPort.getMovementsByWallet(walletId)).thenReturn(List.of(movement));
        when(conversionService.convert(movement, MovementResponse.class)).thenReturn(movementResponse);

        WalletInfoResponse walletInfoResponse = sut.handle(walletId);

        verify(walletPort).getWallet(walletId);
        verify(movementPort).getMovementsByWallet(walletId);
        verify(conversionService).convert(movement, MovementResponse.class);
        assertAll(
                () -> assertEquals(wallet.getBalance(), walletInfoResponse.getBalance()),
                () -> assertEquals(movementResponse, walletInfoResponse.getMovements().get(0))
        );
    }

    @Test
    void itShouldThrowWalletNotFoundException() {
        UUID walletId = UUID.randomUUID();

        when(walletPort.getWallet(walletId)).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> sut.handle(walletId));
    }
}