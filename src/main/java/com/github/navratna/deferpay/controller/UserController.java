package com.github.navratna.deferpay.controller;

import com.github.navratna.deferpay.service.UserService;
import com.github.navratna.deferpay.Request.UserRequest;
import com.github.navratna.deferpay.Response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/repayments") //  Repay global
    public ResponseEntity<Void> repaymentHistory(@RequestBody UserRequest repaymentRequest){
        userService.processRepayment(repaymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }
//
//
    @PostMapping("/repayments/{id}") // Repay for a particular BNPL order
    public ResponseEntity<Void> repayBNPL(@RequestBody UserRequest repaymentRequest){

        userService.processRepayment(repaymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
