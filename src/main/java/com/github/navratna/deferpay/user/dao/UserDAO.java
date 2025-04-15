package com.github.navratna.deferpay.user.dao;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDAO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double creditLimit;
    private Double availableCreditLimit;
    private LocalDateTime createdAt;
}