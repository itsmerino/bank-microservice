package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

    @Override
    public UserEntity convert(User user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .build();
    }
}
