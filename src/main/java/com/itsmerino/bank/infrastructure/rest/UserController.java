package com.itsmerino.bank.infrastructure.rest;

import com.itsmerino.bank.application.createuser.CreateUserUseCase;
import com.itsmerino.bank.application.createuser.dto.CreateUserRequest;
import com.itsmerino.bank.application.createuser.dto.CreateUserResponse;
import com.itsmerino.bank.infrastructure.rest.dto.CreateUserBody;
import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ConversionService conversionService;
    private final CreateUserUseCase createUserUseCase;

    public UserController(ConversionService conversionService,
                          CreateUserUseCase createUserUseCase) {
        this.conversionService = conversionService;
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserBody createUserBody) {
        return Optional.ofNullable(conversionService.convert(createUserBody, CreateUserRequest.class))
                .map(createUserUseCase::handle)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElseThrow();
    }
}
