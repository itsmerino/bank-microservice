package com.itsmerino.bank.application.createwallet;

import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.exception.UserNotFoundException;
import com.itsmerino.bank.domain.ports.UserPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateWalletUseCase {

    private final ConversionService conversionService;
    private final UserPort userPort;
    private final WalletPort walletPort;

    public CreateWalletUseCase(ConversionService conversionService,
                               UserPort userPort,
                               WalletPort walletPort) {
        this.conversionService = conversionService;
        this.userPort = userPort;
        this.walletPort = walletPort;
    }

    public CreateWalletResponse handle(CreateWalletRequest createWalletRequest) {
        validateUserExists(createWalletRequest);

        return Optional.ofNullable(mapCreateWalletRequestToWallet(createWalletRequest))
                .map(walletPort::createWallet)
                .map(this::mapWalletToCreateWalletResponse)
                .orElseThrow();
    }

    private void validateUserExists(CreateWalletRequest createWalletRequest) {
        Optional<User> user = userPort.getUser(createWalletRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
    }

    private Wallet mapCreateWalletRequestToWallet(CreateWalletRequest createWalletRequest) {
        return conversionService.convert(createWalletRequest, Wallet.class);
    }

    private CreateWalletResponse mapWalletToCreateWalletResponse(Wallet wallet) {
        return conversionService.convert(wallet, CreateWalletResponse.class);
    }
}
