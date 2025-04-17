
package com.github.navratna.deferpay.utils;

import com.github.navratna.deferpay.Request.UserRequest;
import com.github.navratna.deferpay.Response.UserResponse;
import com.github.navratna.deferpay.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UsernameGenerator usernameGenerator;

    public UserMapper(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    public UserEntity toEntity(UserRequest userRequest) {
        String username = userRequest.getUsername();
        if (username == null || username.isBlank()) {
            username = usernameGenerator.generateUsername(userRequest.getFirstName());
        }

        return UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .username(username)
                .build();
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .username(userEntity.getUsername())
                .creditLimit(userEntity.getCreditLimit())
                .availableCreditLimit(userEntity.getAvailableCreditLimit())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    public UserResponse toCreditResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .username(userEntity.getUsername())
                .creditLimit(userEntity.getCreditLimit())
                .availableCreditLimit(userEntity.getAvailableCreditLimit())
                .build();
    }
}