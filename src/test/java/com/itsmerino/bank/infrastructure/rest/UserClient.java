package com.itsmerino.bank.infrastructure.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class UserClient {

    private static final String LOCALHOST = "http://localhost:";
    private static final String CREATE_USER_PATH = "/users";
    private static final String CREATE_USER_BODY = "{\"username\":\"%s\"}";

    private final String apiUrl;

    public UserClient(@Value("${server.port}") Integer port,
                      @Value("${server.servlet.context-path}") String contextPath) {
        this.apiUrl = LOCALHOST + port + contextPath + CREATE_USER_PATH;
    }

    public HttpResponse<String> createUser(String username) throws IOException, InterruptedException {
        URI uri = URI.create(apiUrl);
        String body = String.format(CREATE_USER_BODY, username);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
