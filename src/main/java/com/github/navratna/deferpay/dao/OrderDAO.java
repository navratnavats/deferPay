package com.github.navratna.deferpay.dao;

import com.github.navratna.deferpay.common.OrderStatus;
import com.github.navratna.deferpay.common.PaymentType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDAO {
    private Long id;
    private UUID userId;
    private UUID merchantId;
    private Double amount;
    private OrderStatus status;
    private PaymentType paymentType;
    private LocalDateTime createdAt;
    private List<OrderItemDAO> items;
}