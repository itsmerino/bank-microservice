package com.itsmerino.bank.infrastructure.persistence.repository;

import com.itsmerino.bank.infrastructure.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, UUID> {

    List<MovementEntity> findAllByWalletFrom(UUID walletFrom);

    List<MovementEntity> findAllByWalletTo(UUID walletTo);
}
