package com.itsmerino.bank.infrastructure.rest;

import com.itsmerino.bank.application.createwallet.CreateWalletUseCase;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletRequest;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.application.depositfunds.DepositFundsUseCase;
import com.itsmerino.bank.application.depositfunds.dto.DepositFundsRequest;
import com.itsmerino.bank.application.getwalletinfo.GetWalletInfoUseCase;
import com.itsmerino.bank.application.getwalletinfo.dto.WalletInfoResponse;
import com.itsmerino.bank.application.transferfunds.TransferFundsUseCase;
import com.itsmerino.bank.application.transferfunds.dto.TransferFundsRequest;
import com.itsmerino.bank.infrastructure.rest.dto.CreateWalletBody;
import com.itsmerino.bank.infrastructure.rest.dto.DepositFundsBody;
import com.itsmerino.bank.infrastructure.rest.dto.TransferFundsBody;
import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final ConversionService conversionService;
    private final CreateWalletUseCase createWalletUseCase;
    private final GetWalletInfoUseCase getWalletInfoUseCase;
    private final DepositFundsUseCase depositFundsUseCase;
    private final TransferFundsUseCase transferFundsUseCase;

    public WalletController(ConversionService conversionService,
                            CreateWalletUseCase createWalletUseCase,
                            GetWalletInfoUseCase getWalletInfoUseCase,
                            DepositFundsUseCase depositFundsUseCase,
                            TransferFundsUseCase transferFundsUseCase) {
        this.conversionService = conversionService;
        this.createWalletUseCase = createWalletUseCase;
        this.getWalletInfoUseCase = getWalletInfoUseCase;
        this.depositFundsUseCase = depositFundsUseCase;
        this.transferFundsUseCase = transferFundsUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateWalletResponse> createWallet(@Valid @RequestBody CreateWalletBody createWalletBody) {
        return Optional.ofNullable(conversionService.convert(createWalletBody, CreateWalletRequest.class))
                .map(createWalletUseCase::handle)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElseThrow();
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletInfoResponse> getWalletInfo(@PathVariable UUID walletId) {
        WalletInfoResponse walletInfoResponse = getWalletInfoUseCase.handle(walletId);

        return ResponseEntity.ok(walletInfoResponse);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> depositFunds(@Valid @RequestBody DepositFundsBody depositFundsBody) {
        Optional.ofNullable(conversionService.convert(depositFundsBody, DepositFundsRequest.class))
                .ifPresent(depositFundsUseCase::handle);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@Valid @RequestBody TransferFundsBody transferFundsBody) {
        Optional.ofNullable(conversionService.convert(transferFundsBody, TransferFundsRequest.class))
                .ifPresent(transferFundsUseCase::handle);

        return ResponseEntity.noContent().build();
    }
}
