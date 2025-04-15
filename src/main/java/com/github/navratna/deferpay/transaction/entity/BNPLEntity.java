package com.github.navratna.deferpay.transaction.entity;

import com.github.navratna.deferpay.common.BNPLStatus;
import com.github.navratna.deferpay.order.entity.OrderEntity;
import com.github.navratna.deferpay.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bnpl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BNPLEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private Double amount;
    private Double remainingAmount;

    @Enumerated(EnumType.STRING)
    private BNPLStatus status;

    private LocalDate dueDate;
    private LocalDate paidDate;
    private LocalDateTime createdAt;
}
