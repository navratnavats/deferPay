package com.github.navratna.deferpay.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private Double creditLimit;
    private Double availableCreditLimit;
    private LocalDateTime createdAt;
}
