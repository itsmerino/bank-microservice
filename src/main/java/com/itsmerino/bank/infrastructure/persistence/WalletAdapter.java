package com.itsmerino.bank.infrastructure.persistence;

import com.itsmerino.bank.domain.Wallet;
import com.itsmerino.bank.domain.ports.WalletPort;
import com.itsmerino.bank.infrastructure.persistence.entity.WalletEntity;
import com.itsmerino.bank.infrastructure.persistence.repository.WalletRepository;
import com.itsmerino.bank.infrastructure.persistence.web3.BlockchainClient;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WalletAdapter implements WalletPort {

    private final ConversionService conversionService;
    private final BlockchainClient blockchainClient;
    private final WalletRepository walletRepository;

    public WalletAdapter(ConversionService conversionService,
                         BlockchainClient blockchainClient,
                         WalletRepository walletRepository) {
        this.conversionService = conversionService;
        this.blockchainClient = blockchainClient;
        this.walletRepository = walletRepository;
    }

    @Override
    public Optional<Wallet> getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .map(this::mapWalletBalance)
                .map(this::mapWalletEntityToWallet);
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        return Optional.ofNullable(mapWalletToWalletEntity(wallet))
                .map(walletRepository::save)
                .map(this::mapWalletEntityToWallet)
                .orElseThrow();
    }

    private WalletEntity mapWalletBalance(WalletEntity wallet) {
        Integer balance = blockchainClient.getBalance(wallet.getPrivateKey());
        wallet.setBalance(balance);

        return wallet;
    }

    private WalletEntity mapWalletToWalletEntity(Wallet wallet) {
        return conversionService.convert(wallet, WalletEntity.class);
    }

    private Wallet mapWalletEntityToWallet(WalletEntity walletEntity) {
        return conversionService.convert(walletEntity, Wallet.class);
    }
}
