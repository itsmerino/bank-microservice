package com.itsmerino.bank.application.createuser.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserRequestToUserConverter implements Converter<CreateUserRequest, User> {

    @Override
    public User convert(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .build();
    }
}
