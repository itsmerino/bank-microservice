package com.itsmerino.bank.application.getwalletinfo;

import com.itsmerino.bank.application.getwalletinfo.dto.MovementResponse;
import com.itsmerino.bank.application.getwalletinfo.dto.WalletInfoResponse;
import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.exception.WalletNotFoundException;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.domain.ports.WalletPort;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetWalletInfoUseCase {

    private final ConversionService conversionService;
    private final WalletPort walletPort;
    private final MovementPort movementPort;

    public GetWalletInfoUseCase(ConversionService conversionService,
                                MovementPort movementPort,
                                WalletPort walletPort) {
        this.conversionService = conversionService;
        this.movementPort = movementPort;
        this.walletPort = walletPort;
    }

    public WalletInfoResponse handle(UUID walletId) {
        return WalletInfoResponse.builder()
                .balance(getBalance(walletId))
                .movements(getMovements(walletId))
                .build();
    }

    private Integer getBalance(UUID walletId) {
        return walletPort.getWallet(walletId)
                .orElseThrow(WalletNotFoundException::new)
                .getBalance();
    }

    private List<MovementResponse> getMovements(UUID walletId) {
        return movementPort.getMovementsByWallet(walletId)
                .stream()
                .map(this::mapMovementToMovementResponse)
                .toList();
    }

    private MovementResponse mapMovementToMovementResponse(Movement movement) {
        return conversionService.convert(movement, MovementResponse.class);
    }
}
