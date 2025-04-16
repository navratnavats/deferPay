package com.github.navratna.deferpay.user.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class RepaymentRequest {

    private String username;
    private double amount;
}
