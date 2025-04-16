package com.github.navratna.deferpay.user.controller;

import com.github.navratna.deferpay.user.Service.UserService;
import com.github.navratna.deferpay.user.dao.RepaymentRequest;
import com.github.navratna.deferpay.user.dao.UserRequest;
import com.github.navratna.deferpay.user.dao.UserResponse;
import com.github.navratna.deferpay.user.entity.UserEntity;
import com.nimbusds.oauth2.sdk.http.HTTPEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/me")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create") // create user
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
       UserResponse userResponse = userService.createUser(userRequest);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{username}") // Get my profile
    public ResponseEntity<UserResponse> getMyProfile(@PathVariable String username){
        Optional<UserResponse> userResponseOptional = userService.getuserProfile(username);

        return userResponseOptional
                .map(userResponse -> new ResponseEntity<>(userResponse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/credit/{username}") // View current credit and available balance
    public ResponseEntity<UserResponse> viewCredit(@PathVariable String username){

        return userService.getCredit(username);

    }
//
//    @GetMapping("/orders") // List all orders (BNPL & regular)
//    public ResponseEntity<> allOrders(){
//
//    }
//
    @GetMapping("/repayments") // View repayment history
    public ResponseEntity<> repaymentHistory(@RequestBody RepaymentRequest){


    }
//
//
//    @PostMapping("/repayments/{id}") // Repay for a BNPL order
//    public ResponseEntity<> repayBNPL(){
//
//    }

}
