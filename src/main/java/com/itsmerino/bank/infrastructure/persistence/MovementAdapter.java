package com.itsmerino.bank.infrastructure.persistence;

import com.itsmerino.bank.domain.Movement;
import com.itsmerino.bank.domain.ports.MovementPort;
import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import com.itsmerino.bank.infrastructure.persistence.repository.MovementRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class MovementAdapter implements MovementPort {

    private final ConversionService conversionService;
    private final MovementRepository movementRepository;

    public MovementAdapter(ConversionService conversionService,
                           MovementRepository movementRepository) {
        this.conversionService = conversionService;
        this.movementRepository = movementRepository;
    }

    @Override
    public List<Movement> getMovementsByWallet(UUID walletId) {
        List<MovementEntity> movementsFrom = movementRepository.findAllByWalletFrom(walletId);
        List<MovementEntity> movementsTo = movementRepository.findAllByWalletTo(walletId);

        return Stream.of(movementsFrom, movementsTo)
                .flatMap(List::stream)
                .map(this::mapMovementEntityToMovement)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Movement::getDate))
                .toList();
    }

    @Override
    public Movement createMovement(Movement movement) {
        return Optional.ofNullable(mapMovementToMovementEntity(movement))
                .map(movementRepository::save)
                .map(this::mapMovementEntityToMovement)
                .orElseThrow();
    }

    private MovementEntity mapMovementToMovementEntity(Movement movement) {
        return conversionService.convert(movement, MovementEntity.class);
    }

    private Movement mapMovementEntityToMovement(MovementEntity movementEntity) {
        return conversionService.convert(movementEntity, Movement.class);
    }
}
