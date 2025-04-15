package com.github.navratna.deferpay.user.entity;

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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private Double creditLimit;
    private Double availableCreditLimit;

    private LocalDateTime createdAt;
}
