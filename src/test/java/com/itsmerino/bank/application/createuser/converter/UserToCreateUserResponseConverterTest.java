package com.itsmerino.bank.application.createuser.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.UserMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserToCreateUserResponseConverterTest {

    private final UserToCreateUserResponseConverter sut = new UserToCreateUserResponseConverter();

    @Test
    void itShouldConvertUserToCreateUserResponse() {
        User user = UserMother.random();

        CreateUserResponse createUserResponse = sut.convert(user);

        assertNotNull(createUserResponse);
        assertAll(
                () -> assertEquals(user.getId(), createUserResponse.getId()),
                () -> assertEquals(user.getUsername(), createUserResponse.getUsername())
        );
    }
}