package com.itsmerino.bank.domain.ports;

import com.itsmerino.bank.domain.Movement;

import java.util.List;
import java.util.UUID;

public interface MovementPort {

    List<Movement> getMovementsByWallet(UUID walletId);

    Movement createMovement(Movement movement);
}
