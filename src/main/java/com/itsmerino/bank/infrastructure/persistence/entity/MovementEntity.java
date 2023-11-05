package com.itsmerino.bank.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movements")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "wallet_from")
    private UUID walletFrom;

    @Column(name = "wallet_to")
    private UUID walletTo;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    private LocalDateTime date;
}
