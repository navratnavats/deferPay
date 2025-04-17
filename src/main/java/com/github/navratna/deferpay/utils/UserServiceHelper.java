package com.github.navratna.deferpay.utils;

import com.github.navratna.deferpay.Request.UserRequest;
import com.github.navratna.deferpay.Response.UserResponse;
import com.github.navratna.deferpay.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHelper {

    private final UsernameGenerator usernameGenerator;

    public UserServiceHelper(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    public UserEntity convertToEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPhone(userRequest.getPhone());

        userEntity.setUsername(usernameGenerator.generateUsername(userRequest.getFirstName()));

        return userEntity;
    }


    public UserResponse getCreditResponse(UserEntity userEntity){
        return UserResponse.builder()
                .username(userEntity.getUsername())
                .creditLimit(userEntity.getCreditLimit())
                .build();
    }
    public UserResponse getUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .availableCreditLimit(userEntity.getAvailableCreditLimit())
                .creditLimit(userEntity.getCreditLimit())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phone(userEntity.getPhone())
                .username(userEntity.getUsername())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

}
