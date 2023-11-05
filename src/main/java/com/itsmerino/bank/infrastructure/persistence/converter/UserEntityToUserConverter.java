package com.itsmerino.bank.infrastructure.persistence.converter;

import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {

    @Override
    public User convert(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }
}
