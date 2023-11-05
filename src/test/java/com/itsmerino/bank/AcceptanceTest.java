package com.itsmerino.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.application.createwallet.dto.CreateWalletResponse;
import com.itsmerino.bank.infrastructure.rest.UserClient;
import com.itsmerino.bank.infrastructure.rest.WalletClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class AcceptanceTest {

    private final ObjectMapper objectMapper;
    private final UserClient userClient;
    private final WalletClient walletClient;

    private final Map<String, UUID> users;
    private final Map<String, UUID> wallets;

    private HttpResponse<String> lastResponse;

    @Autowired
    private AcceptanceTest(ObjectMapper objectMapper,
                           UserClient userClient,
                           WalletClient walletClient) {
        this.objectMapper = objectMapper;
        this.userClient = userClient;
        this.walletClient = walletClient;
        this.users = new HashMap<>();
        this.wallets = new HashMap<>();
    }

    @Given("a user {string} is registered")
    public void aUserIsRegistered(String username) throws IOException, InterruptedException {
        HttpResponse<String> response = userClient.createUser(username);
        CreateUserResponse createUserResponse = objectMapper.readValue(response.body(), CreateUserResponse.class);
        users.put(username, createUserResponse.getId());
    }

    @Given("{string} has a wallet with address {string} and privateKey {string}")
    public void hasAWalletWithAddressAndPrivateKey(String username,
                                                   String address,
                                                   String privateKey) throws IOException, InterruptedException {
        HttpResponse<String> response = walletClient.createWallet(users.get(username), address, privateKey);
        CreateWalletResponse createWalletResponse = objectMapper.readValue(response.body(), CreateWalletResponse.class);
        wallets.put(username, createWalletResponse.getId());
    }

    @When("the user {string} makes a deposit into his wallet")
    public void theUserMakesADepositIntoHisWallet(String username) throws IOException, InterruptedException {
        lastResponse = walletClient.depositFunds(wallets.get(username), 10);
    }

    @When("the user {string} makes a transfer to {string}")
    public void theUserMakesATransferTo(String usernameOne,
                                        String usernameTwo) throws IOException, InterruptedException {
        lastResponse = walletClient.transferFunds(wallets.get(usernameOne), wallets.get(usernameTwo), 1);
    }

    @Then("the deposit is done successfully")
    public void theDepositIsDoneSuccessfully() {
        assertEquals(HttpStatus.NO_CONTENT.value(), lastResponse.statusCode());
    }

    @Then("the transfer is done successfully")
    public void theTransferIsDoneSuccessfully() {
        assertEquals(HttpStatus.NO_CONTENT.value(), lastResponse.statusCode());
    }
}
