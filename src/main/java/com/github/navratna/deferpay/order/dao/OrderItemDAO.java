package com.github.navratna.deferpay.order.dao;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDAO {
    private Long id;
    private UUID orderId;
    private UUID productId;
    private String productName;
    private Double pricePerUnit;
    private int quantity;
}