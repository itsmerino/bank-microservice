package com.itsmerino.bank.domain.ports;

import com.itsmerino.bank.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {

    Optional<User> getUser(UUID userId);

    User createUser(User user);
}
