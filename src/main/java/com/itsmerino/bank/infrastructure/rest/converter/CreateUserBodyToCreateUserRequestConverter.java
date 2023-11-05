package com.itsmerino.bank.infrastructure.rest.converter;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.infrastructure.rest.dto.CreateUserBody;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserBodyToCreateUserRequestConverter implements Converter<CreateUserBody, CreateUserRequest> {

    @Override
    public CreateUserRequest convert(CreateUserBody createUserBody) {
        return CreateUserRequest.builder()
                .username(createUserBody.getUsername())
                .build();
    }
}
