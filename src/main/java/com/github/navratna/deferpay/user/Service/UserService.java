package com.github.navratna.deferpay.user.Service;

import com.github.navratna.deferpay.user.dao.RepaymentRequest;
import com.github.navratna.deferpay.user.dao.UserRequest;
import com.github.navratna.deferpay.user.dao.UserResponse;
import com.github.navratna.deferpay.user.entity.UserEntity;
import com.github.navratna.deferpay.user.repository.UserRepository;
import com.github.navratna.deferpay.user.util.UserServiceHelper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableCaching
public class UserService {


    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;

    public UserService(UserRepository userRepository, UserServiceHelper userServiceHelper) {
        this.userRepository = userRepository;
        this.userServiceHelper = userServiceHelper;
    }

    public UserResponse createUser(UserRequest userRequest){
        UserEntity userEntity = userServiceHelper.convertToEntity(userRequest);

        userRepository.save(userEntity);

        return userServiceHelper.getUserResponse(userEntity);
    }

    @Cacheable(key = "#username", value = "usercache")
    public ResponseEntity<UserResponse> getCredit(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);

        if (optionalUserEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UserResponse userResponse = userServiceHelper.getCreditResponse(optionalUserEntity.get());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }

    @Cacheable(key = "#username", value = "userCache")
    public Optional<UserResponse> getuserProfile(String username){

        Optional<UserEntity> optionalUserEntity =  userRepository.findByUsername(username);

        return optionalUserEntity.map(userServiceHelper::getUserResponse);
    }


    public void userRepayemnt(RepaymentRequest repaymentRequest){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(repaymentRequest.getUsername());

        double creditLimit = 0;
        double availableLimit;
        double dues;

        UserEntity userEntity = null;
        if(optionalUserEntity.isPresent()){
             userEntity = optionalUserEntity.get();

             creditLimit = userEntity.getCreditLimit();
             availableLimit = userEntity.getAvailableCreditLimit();

             double repaymentAmount = repaymentRequest.getAmount();



        }


    }

}
