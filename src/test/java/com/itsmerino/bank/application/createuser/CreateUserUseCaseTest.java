package com.itsmerino.bank.application.createuser;

import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.application.createuser.dto.CreateUserRequestMother;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponseMother;
import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.UserMother;
import com.itsmerino.bank.domain.ports.UserPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final UserPort userPort = mock(UserPort.class);
    private final CreateUserUseCase sut = new CreateUserUseCase(conversionService, userPort);

    @Test
    void itShouldCreateUser() {
        User user = UserMother.random();
        CreateUserRequest createUserRequest = CreateUserRequestMother.random();
        CreateUserResponse createUserResponse = CreateUserResponseMother.random();

        when(conversionService.convert(createUserRequest, User.class)).thenReturn(user);
        when(userPort.createUser(user)).thenReturn(user);
        when(conversionService.convert(user, CreateUserResponse.class)).thenReturn(createUserResponse);

        sut.handle(createUserRequest);

        verify(conversionService).convert(createUserRequest, User.class);
        verify(userPort).createUser(user);
        verify(conversionService).convert(user, CreateUserResponse.class);
    }
}
