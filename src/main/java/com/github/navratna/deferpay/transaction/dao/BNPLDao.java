package com.github.navratna.deferpay.transaction.dao;

import com.github.navratna.deferpay.common.BNPLStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BNPLDao {
    private UUID id;
    private UUID userId;
    private UUID orderId;
    private Double amount;
    private Double remainingAmount;
    private BNPLStatus status;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private LocalDateTime createdAt;
}
