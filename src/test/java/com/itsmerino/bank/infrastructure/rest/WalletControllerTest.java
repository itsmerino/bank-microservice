package com.itsmerino.bank.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsmerino.bank.BankApplication;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.infrastructure.rest.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WalletControllerTest {

    private final ObjectMapper objectMapper;
    private final UserClient userClient;
    private final WalletClient walletClient;

    private final String walletOneAddress;
    private final String walletOnePrivateKey;
    private final String walletTwoAddress;
    private final String walletTwoPrivateKey;
    private final String walletThreeAddress;
    private final String walletThreePrivateKey;

    @Autowired
    public WalletControllerTest(ObjectMapper objectMapper,
                                UserClient userClient,
                                WalletClient walletClient,
                                @Value("${web3.wallet1.address}") String walletOneAddress,
                                @Value("${web3.wallet1.privateKey}") String walletOnePrivateKey,
                                @Value("${web3.wallet2.address}") String walletTwoAddress,
                                @Value("${web3.wallet2.privateKey}") String walletTwoPrivateKey,
                                @Value("${web3.wallet3.address}") String walletThreeAddress,
                                @Value("${web3.wallet3.privateKey}") String walletThreePrivateKey) {
        this.objectMapper = objectMapper;
        this.userClient = userClient;
        this.walletClient = walletClient;
        this.walletOneAddress = walletOneAddress;
        this.walletOnePrivateKey = walletOnePrivateKey;
        this.walletTwoAddress = walletTwoAddress;
        this.walletTwoPrivateKey = walletTwoPrivateKey;
        this.walletThreeAddress = walletThreeAddress;
        this.walletThreePrivateKey = walletThreePrivateKey;
    }

    @Test
    void itShouldCreateWallet() throws IOException, InterruptedException {
        HttpResponse<String> userResponse = userClient.createUser("user-001");
        CreateUserResponse createUserResponse = objectMapper.readValue(userResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletResponse = walletClient.createWallet(createUserResponse.getId(), walletOneAddress, walletOnePrivateKey);

        assertEquals(HttpStatus.CREATED.value(), walletResponse.statusCode());
        assertFalse(walletResponse.body().isEmpty());
    }

    @Test
    void itShouldGetWalletInfo() throws IOException, InterruptedException {
        HttpResponse<String> userResponse = userClient.createUser("user-002");
        CreateUserResponse createUserResponse = objectMapper.readValue(userResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletResponse = walletClient.createWallet(createUserResponse.getId(), walletOneAddress, walletOnePrivateKey);
        CreateWalletResponse createWalletResponse = objectMapper.readValue(walletResponse.body(), CreateWalletResponse.class);

        HttpResponse<String> walletInfoResponse = walletClient.getWalletInfo(createWalletResponse.getId());

        assertEquals(HttpStatus.OK.value(), walletInfoResponse.statusCode());
        assertFalse(walletInfoResponse.body().isEmpty());
    }

    @Test
    void itShouldDepositFunds() throws IOException, InterruptedException {
        HttpResponse<String> userResponse = userClient.createUser("user-003");
        CreateUserResponse createUserResponse = objectMapper.readValue(userResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletResponse = walletClient.createWallet(createUserResponse.getId(), walletOneAddress, walletOnePrivateKey);
        CreateWalletResponse createWalletResponse = objectMapper.readValue(walletResponse.body(), CreateWalletResponse.class);

        HttpResponse<String> depositFundsResponse = walletClient.depositFunds(createWalletResponse.getId(), 1);

        assertEquals(HttpStatus.NO_CONTENT.value(), depositFundsResponse.statusCode());
    }

    @Test
    void itShouldTransferFunds() throws IOException, InterruptedException {
        HttpResponse<String> userOneResponse = userClient.createUser("user-004");
        HttpResponse<String> userTwoResponse = userClient.createUser("user-005");
        CreateUserResponse createUserOneResponse = objectMapper.readValue(userOneResponse.body(), CreateUserResponse.class);
        CreateUserResponse createUserTwoResponse = objectMapper.readValue(userTwoResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletOneResponse = walletClient.createWallet(createUserOneResponse.getId(), walletOneAddress, walletOnePrivateKey);
        HttpResponse<String> walletTwoResponse = walletClient.createWallet(createUserTwoResponse.getId(), walletTwoAddress, walletTwoPrivateKey);
        CreateWalletResponse createWalletOneResponse = objectMapper.readValue(walletOneResponse.body(), CreateWalletResponse.class);
        CreateWalletResponse createWalletTwoResponse = objectMapper.readValue(walletTwoResponse.body(), CreateWalletResponse.class);

        walletClient.depositFunds(createWalletOneResponse.getId(), 1);
        HttpResponse<String> transferFundsResponse = walletClient.transferFunds(createWalletOneResponse.getId(), createWalletTwoResponse.getId(), 1);

        assertEquals(HttpStatus.NO_CONTENT.value(), transferFundsResponse.statusCode());
    }

    @Test
    void itShouldReturnBadRequestExceptionWhenUserNotFound() throws IOException, InterruptedException {
        HttpResponse<String> walletResponse = walletClient.createWallet(UUID.randomUUID(), walletOneAddress, walletOnePrivateKey);
        ErrorResponse errorResponse = objectMapper.readValue(walletResponse.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), walletResponse.statusCode());
        assertEquals("User not found", errorResponse.getMessage());
    }

    @Test
    void itShouldReturnBadRequestExceptionWhenWhenWalletNotFound() throws IOException, InterruptedException {
        HttpResponse<String> transferFundsResponse = walletClient.depositFunds(UUID.randomUUID(), 1);
        ErrorResponse errorResponse = objectMapper.readValue(transferFundsResponse.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), transferFundsResponse.statusCode());
        assertEquals("Wallet not found", errorResponse.getMessage());
    }

    @Test
    void itShouldReturnBadRequestExceptionWhenTransferToSameWallet() throws IOException, InterruptedException {
        HttpResponse<String> userResponse = userClient.createUser("user-006");
        CreateUserResponse createUserResponse = objectMapper.readValue(userResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletResponse = walletClient.createWallet(createUserResponse.getId(), walletOneAddress, walletOnePrivateKey);
        CreateWalletResponse createWalletResponse = objectMapper.readValue(walletResponse.body(), CreateWalletResponse.class);

        HttpResponse<String> transferFundsResponse = walletClient.transferFunds(createWalletResponse.getId(), createWalletResponse.getId(), 1);
        ErrorResponse errorResponse = objectMapper.readValue(transferFundsResponse.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), transferFundsResponse.statusCode());
        assertEquals("From and to wallets can not be the same", errorResponse.getMessage());
    }

    @Test
    void itShouldReturnBadRequestExceptionWhenInsufficientFunds() throws IOException, InterruptedException {
        HttpResponse<String> userOneResponse = userClient.createUser("user-007");
        HttpResponse<String> userTwoResponse = userClient.createUser("user-008");
        CreateUserResponse createUserOneResponse = objectMapper.readValue(userOneResponse.body(), CreateUserResponse.class);
        CreateUserResponse createUserTwoResponse = objectMapper.readValue(userTwoResponse.body(), CreateUserResponse.class);

        HttpResponse<String> walletOneResponse = walletClient.createWallet(createUserOneResponse.getId(), walletThreeAddress, walletThreePrivateKey);
        HttpResponse<String> walletTwoResponse = walletClient.createWallet(createUserTwoResponse.getId(), walletTwoAddress, walletTwoPrivateKey);
        CreateWalletResponse createWalletOneResponse = objectMapper.readValue(walletOneResponse.body(), CreateWalletResponse.class);
        CreateWalletResponse createWalletTwoResponse = objectMapper.readValue(walletTwoResponse.body(), CreateWalletResponse.class);

        HttpResponse<String> transferFundsResponse = walletClient.transferFunds(createWalletOneResponse.getId(), createWalletTwoResponse.getId(), 1);
        ErrorResponse errorResponse = objectMapper.readValue(transferFundsResponse.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), transferFundsResponse.statusCode());
        assertEquals("Insufficient funds", errorResponse.getMessage());
    }
}
