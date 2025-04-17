package com.github.navratna.deferpay.models;

import com.github.navratna.deferpay.common.BNPLStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bnpl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BNPL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bnplId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Double amount;
    private Double remainingAmount;

    @Enumerated(EnumType.STRING)
    private BNPLStatus status;

    private LocalDate dueDate;
    private LocalDate paidDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
