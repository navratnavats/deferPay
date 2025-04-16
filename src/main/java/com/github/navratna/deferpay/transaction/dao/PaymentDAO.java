package com.github.navratna.deferpay.transaction.dao;

import com.github.navratna.deferpay.common.PaymentStatus;
import com.github.navratna.deferpay.common.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDAO {
    private Long id;
    private UUID userId;
    private UUID orderId;
    private Double amount;
    private PaymentType type;
    private PaymentStatus status;
    private LocalDateTime paidAt;
}
