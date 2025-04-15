package com.github.navratna.deferpay.merchant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantEntity {
    @Id
    private UUID id;

    private String name;
    private String email;
    private String phone;

    private LocalDateTime createdAt;
}

