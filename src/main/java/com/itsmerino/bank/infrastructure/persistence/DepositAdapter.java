package com.itsmerino.bank.infrastructure.persistence;

import com.itsmerino.bank.domain.Deposit;
import com.itsmerino.bank.domain.ports.DepositPort;
import com.itsmerino.bank.infrastructure.persistence.web3.BlockchainClient;
import org.springframework.stereotype.Component;

@Component
public class DepositAdapter implements DepositPort {

    private final BlockchainClient blockchainClient;

    public DepositAdapter(BlockchainClient blockchainClient) {
        this.blockchainClient = blockchainClient;
    }

    @Override
    public void depositFunds(Deposit deposit) {
        blockchainClient.depositFunds(
                deposit.getWallet().getPrivateKey(),
                deposit.getAmount()
        );
    }
}
