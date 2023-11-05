package com.itsmerino.bank.application.createuser.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToCreateUserResponseConverter implements Converter<User, CreateUserResponse> {

    @Override
    public CreateUserResponse convert(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
