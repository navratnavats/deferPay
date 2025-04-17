package com.github.navratna.deferpay.dao;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDAO {
    private Long id;
    private String name;
    private Double cost;
    private int quantity;
    private boolean isActive;
    private LocalDateTime createdAt;
    private UUID merchantId;
}
