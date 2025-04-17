package com.github.navratna.deferpay.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private String username;
    private double amount;
    private Long orderID;
}