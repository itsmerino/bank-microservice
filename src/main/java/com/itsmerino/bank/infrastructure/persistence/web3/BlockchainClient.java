package com.itsmerino.bank.infrastructure.persistence.web3;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Component
public class BlockchainClient {

    private final Web3j web3j;
    private final String contract;

    @SneakyThrows
    public BlockchainClient(@Value("${web3.url}") String url,
                            @Value("${web3.contract.privateKey}") String privateKey) {
        HttpService httpService = new HttpService(url);
        this.web3j = Web3j.build(httpService);
        this.contract = deployBankContract(privateKey).getContractAddress();
    }

    @SneakyThrows
    public void depositFunds(String privateKey,
                             Integer amount) {
        BankContract bankContract = loadBankContract(privateKey);
        bankContract.deposit(BigInteger.valueOf(amount)).send();
    }

    @SneakyThrows
    public void transferFunds(String privateKey,
                              String toAddress,
                              Integer amount) {
        BankContract bankContract = loadBankContract(privateKey);
        bankContract.transfer(toAddress, BigInteger.valueOf(amount)).send();
    }

    @SneakyThrows
    public Integer getBalance(String privateKey) {
        BankContract bankContract = loadBankContract(privateKey);
        return bankContract.getBalance().send().intValue();
    }

    @SneakyThrows
    private BankContract deployBankContract(String privateKey) {
        return BankContract.deploy(web3j, Credentials.create(privateKey), getGasProvider()).send();
    }

    @SneakyThrows
    private BankContract loadBankContract(String privateKey) {
        return BankContract.load(contract, web3j, Credentials.create(privateKey), getGasProvider());
    }

    @SneakyThrows
    private StaticGasProvider getGasProvider() {
        EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
        BigInteger gasLimit = block.getGasLimit();

        return new StaticGasProvider(gasPrice, gasLimit);
    }
}
