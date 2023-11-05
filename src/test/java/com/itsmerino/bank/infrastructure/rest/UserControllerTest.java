package com.itsmerino.bank.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsmerino.bank.BankApplication;
import com.itsmerino.bank.infrastructure.rest.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest {

    private final ObjectMapper objectMapper;
    private final UserClient userClient;

    @Autowired
    public UserControllerTest(ObjectMapper objectMapper,
                              UserClient userClient) {
        this.objectMapper = objectMapper;
        this.userClient = userClient;
    }

    @Test
    void itShouldCreateUser() throws IOException, InterruptedException {
        HttpResponse<String> response = userClient.createUser("dummy-001");

        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        assertFalse(response.body().isEmpty());
    }

    @Test
    void itShouldReturnBadRequestException() throws IOException, InterruptedException {
        HttpResponse<String> firstResponse = userClient.createUser("dummy-002");
        HttpResponse<String> secondResponse = userClient.createUser("dummy-002");
        ErrorResponse errorResponse = objectMapper.readValue(secondResponse.body(), ErrorResponse.class);

        assertEquals(HttpStatus.CREATED.value(), firstResponse.statusCode());
        assertFalse(firstResponse.body().isEmpty());

        assertEquals(HttpStatus.BAD_REQUEST.value(), secondResponse.statusCode());
        assertEquals("Username already exists", errorResponse.getMessage());
    }
}
