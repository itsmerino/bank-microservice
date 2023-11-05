package com.itsmerino.bank.infrastructure.persistence;

import com.itsmerino.bank.domain.User;
import com.itsmerino.bank.domain.ports.UserPort;
import com.itsmerino.bank.infrastructure.persistence.entity.UserEntity;
import com.itsmerino.bank.infrastructure.persistence.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserAdapter implements UserPort {

    private final ConversionService conversionService;
    private final UserRepository userRepository;

    public UserAdapter(ConversionService conversionService,
                       UserRepository userRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return userRepository.findById(userId)
                .map(this::mapUserEntityToUser);
    }

    @Override
    public User createUser(User user) {
        return Optional.ofNullable(mapUserToUserEntity(user))
                .map(userRepository::save)
                .map(this::mapUserEntityToUser)
                .orElseThrow();
    }

    private UserEntity mapUserToUserEntity(User user) {
        return conversionService.convert(user, UserEntity.class);
    }

    private User mapUserEntityToUser(UserEntity u) {
        return conversionService.convert(u, User.class);
    }
}
