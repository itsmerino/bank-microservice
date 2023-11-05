package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.infrastructure.rest.dto.CreateUserBody;
import com.itsmerino.bank.infrastructure.rest.dto.CreateUserBodyMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateUserBodyToCreateUserRequestConverterTest {

    private final CreateUserBodyToCreateUserRequestConverter sut = new CreateUserBodyToCreateUserRequestConverter();

    @Test
    void itShouldConvertCreateUserBodyToCreateUserRequest() {
        CreateUserBody createUserBody = CreateUserBodyMother.random();

        CreateUserRequest createUserRequest = sut.convert(createUserBody);

        assertNotNull(createUserRequest);
        assertEquals(createUserBody.getUsername(), createUserRequest.getUsername());
    }
}