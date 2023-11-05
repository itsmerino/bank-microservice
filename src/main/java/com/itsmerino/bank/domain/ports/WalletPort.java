package com.itsmerino.bank.domain.ports;

import com.itsmerino.bank.domain.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface WalletPort {

    Optional<Wallet> getWallet(UUID walletId);

    Wallet createWallet(Wallet wallet);
}
