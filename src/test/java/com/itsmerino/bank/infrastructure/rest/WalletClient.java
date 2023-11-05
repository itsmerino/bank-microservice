package com.itsmerino.bank.infrastructure.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Component
public class WalletClient {

    private static final String LOCALHOST = "http://localhost:";
    private static final String CREATE_WALLET_PATH = "/wallets";
    private static final String DEPOSIT_FUNDS_PATH = "/wallets/deposit";
    private static final String TRANSFER_FUNDS_PATH = "/wallets/transfer";
    private static final String GET_WALLET_INFO_PATH = "/wallets/";
    private static final String CREATE_WALLET_BODY = "{\"userId\":\"%s\",\"address\":\"%s\",\"privateKey\":\"%s\"}";
    private static final String DEPOSIT_FUNDS_BODY = "{\"walletId\":\"%s\",\"amount\":\"%d\"}";
    private static final String TRANSFER_FUNDS_BODY = "{\"walletFrom\":\"%s\",\"walletTo\":\"%s\",\"amount\":\"%d\"}";

    private final String apiUrl;

    public WalletClient(@Value("${server.port}") Integer port,
                        @Value("${server.servlet.context-path}") String contextPath) {
        this.apiUrl = LOCALHOST + port + contextPath;
    }

    public HttpResponse<String> createWallet(UUID userId,
                                             String address,
                                             String privateKey) throws IOException, InterruptedException {
        URI uri = URI.create(apiUrl + CREATE_WALLET_PATH);
        String body = String.format(CREATE_WALLET_BODY, userId, address, privateKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getWalletInfo(UUID walletId) throws IOException, InterruptedException {
        URI uri = URI.create(apiUrl + GET_WALLET_INFO_PATH + walletId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> depositFunds(UUID walletId,
                                             Integer amount) throws IOException, InterruptedException {
        URI uri = URI.create(apiUrl + DEPOSIT_FUNDS_PATH);
        String body = String.format(DEPOSIT_FUNDS_BODY, walletId, amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> transferFunds(UUID walletFrom,
                                              UUID walletTo,
                                              Integer amount) throws IOException, InterruptedException {
        URI uri = URI.create(apiUrl + TRANSFER_FUNDS_PATH);
        String body = String.format(TRANSFER_FUNDS_BODY, walletFrom, walletTo, amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
