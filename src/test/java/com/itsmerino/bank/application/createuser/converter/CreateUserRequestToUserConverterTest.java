package com.itsmerino.bank.application.createuser.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.application.createuser.dto.CreateUserRequestMother;
import com.itsmerino.bank.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateUserRequestToUserConverterTest {

    private final CreateUserRequestToUserConverter sut = new CreateUserRequestToUserConverter();

    @Test
    void itShouldConvertCreateUserRequestToUser() {
        CreateUserRequest createUserRequest = CreateUserRequestMother.random();

        User user = sut.convert(createUserRequest);

        assertNotNull(user);
        assertEquals(createUserRequest.getUsername(), user.getUsername());
    }
}