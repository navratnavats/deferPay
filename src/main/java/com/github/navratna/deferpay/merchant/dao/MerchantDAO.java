package com.github.navratna.deferpay.merchant.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantDAO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}