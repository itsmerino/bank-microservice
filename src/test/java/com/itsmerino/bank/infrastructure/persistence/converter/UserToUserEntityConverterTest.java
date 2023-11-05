package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.UserMother;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserToUserEntityConverterTest {

    private final UserToUserEntityConverter sut = new UserToUserEntityConverter();

    @Test
    void itShouldConvertUserToUserEntity() {
        User user = UserMother.random();

        UserEntity userEntity = sut.convert(user);

        assertNotNull(userEntity);
        assertEquals(user.getUsername(), userEntity.getUsername());
    }
}