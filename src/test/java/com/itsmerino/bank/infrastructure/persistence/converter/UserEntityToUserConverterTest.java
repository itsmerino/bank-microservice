package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntity;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntityMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityToUserConverterTest {

    private final UserEntityToUserConverter sut = new UserEntityToUserConverter();

    @Test
    void itShouldConvertUserEntityToUser() {
        UserEntity userEntity = UserEntityMother.random();

        User user = sut.convert(userEntity);

        assertNotNull(user);
        assertAll(
                () -> assertEquals(userEntity.getId(), user.getId()),
                () -> assertEquals(userEntity.getUsername(), user.getUsername())
        );
    }
}