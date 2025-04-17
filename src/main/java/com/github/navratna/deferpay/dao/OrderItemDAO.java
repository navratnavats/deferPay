package com.github.navratna.deferpay.dao;

import lombok.*;

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