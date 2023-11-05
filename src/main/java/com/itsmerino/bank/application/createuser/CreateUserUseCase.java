package com.itsmerino.bank.application.createuser;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.ports.UserPort;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserUseCase {

    private final ConversionService conversionService;
    private final UserPort userPort;

    public CreateUserUseCase(ConversionService conversionService,
                             UserPort userPort) {
        this.conversionService = conversionService;
        this.userPort = userPort;
    }

    public CreateUserResponse handle(CreateUserRequest createUserRequest) {
        return Optional.ofNullable(mapCreateUserRequestToUser(createUserRequest))
                .map(userPort::createUser)
                .map(this::mapUserToCreateUserResponse)
                .orElseThrow();
    }

    private User mapCreateUserRequestToUser(CreateUserRequest createUserRequest) {
        return conversionService.convert(createUserRequest, User.class);
    }

    private CreateUserResponse mapUserToCreateUserResponse(User user) {
        return conversionService.convert(user, CreateUserResponse.class);
    }
}
