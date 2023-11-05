package com.itsmerino.bank.application.createwallet;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequestMother;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponseMother;
import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.UserMother;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.WalletMother;
import com.itsmerino.bank.domain.exception.UserNotFoundException;
import com.itsmerino.bank.domain.ports.UserPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateWalletUseCaseTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final UserPort userPort = mock(UserPort.class);
    private final WalletPort walletPort = mock(WalletPort.class);
    private final CreateWalletUseCase sut = new CreateWalletUseCase(conversionService, userPort, walletPort);

    @Test
    void itShouldCreateWallet() {
        User user = UserMother.random();
        Wallet wallet = WalletMother.random();
        CreateWalletRequest createWalletRequest = CreateWalletRequestMother.random();
        CreateWalletResponse createWalletResponse = CreateWalletResponseMother.random();

        when(userPort.getUser(createWalletRequest.getUserId())).thenReturn(Optional.of(user));
        when(conversionService.convert(createWalletRequest, Wallet.class)).thenReturn(wallet);
        when(walletPort.createWallet(wallet)).thenReturn(wallet);
        when(conversionService.convert(wallet, CreateWalletResponse.class)).thenReturn(createWalletResponse);

        sut.handle(createWalletRequest);

        verify(userPort).getUser(createWalletRequest.getUserId());
        verify(conversionService).convert(createWalletRequest, Wallet.class);
        verify(walletPort).createWallet(wallet);
        verify(conversionService).convert(wallet, CreateWalletResponse.class);
    }

    @Test
    void itShouldThrowUserNotFoundException() {
        CreateWalletRequest createWalletRequest = CreateWalletRequestMother.random();

        when(userPort.getUser(createWalletRequest.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> sut.handle(createWalletRequest));
    }
}