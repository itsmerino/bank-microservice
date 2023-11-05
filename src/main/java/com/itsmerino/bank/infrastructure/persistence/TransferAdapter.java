package com.itsmerino.bank.infrastructure.persistence;

import com.itsmerino.bank.domain.Transfer;
import com.itsmerino.bank.domain.ports.TransferPort;
import com.itsmerino.bank.infrastructure.persistence.web3.BlockchainClient;
import org.springframework.stereotype.Component;

@Component
public class TransferAdapter implements TransferPort {

    private final BlockchainClient blockchainClient;

    public TransferAdapter(BlockchainClient blockchainClient) {
        this.blockchainClient = blockchainClient;
    }

    @Override
    public void transferFunds(Transfer transfer) {
        blockchainClient.transferFunds(
                transfer.getWalletFrom().getPrivateKey(),
                transfer.getWalletTo().getAddress(),
                transfer.getAmount()
        );
    }
}
