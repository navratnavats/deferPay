package com.github.navratna.deferpay.service;

import com.github.navratna.deferpay.common.BNPLStatus;
import com.github.navratna.deferpay.models.BNPLEntity;
import com.github.navratna.deferpay.repositories.BNPLRepository;
import com.github.navratna.deferpay.Request.UserRequest;
import com.github.navratna.deferpay.Response.UserResponse;
import com.github.navratna.deferpay.models.UserEntity;
import com.github.navratna.deferpay.repositories.UserRepository;
import com.github.navratna.deferpay.utils.UserServiceHelper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class UserService {


    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final BNPLRepository bnplRepository;

    public UserService(UserRepository userRepository, UserServiceHelper userServiceHelper, BNPLRepository bnplRepository) {
        this.userRepository = userRepository;
        this.userServiceHelper = userServiceHelper;
        this.bnplRepository = bnplRepository;
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


    public void processRepayment(UserRequest userRequest) {

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userRequest.getUsername());
        if(userRequest.getOrderID() == null) {
            if (optionalUserEntity.isPresent()) {
                Optional<List<BNPLEntity>> optionalBNPLEntities = bnplRepository.findAllByUser_Username(userRequest.getUsername());
                if (optionalBNPLEntities.isPresent() && !optionalBNPLEntities.get().isEmpty()) {
                    List<BNPLEntity> bnplEntities = optionalBNPLEntities.get();
                    bnplEntities.sort(Comparator.comparing(BNPLEntity::getDueDate));

                    double repaidAmount = userRequest.getAmount();

                    for (BNPLEntity bnplEntity : bnplEntities) {
                        if (bnplEntity.getRemainingAmount() <= repaidAmount) {
                            repaidAmount = repaidAmount - bnplEntity.getRemainingAmount();

                            bnplEntity.setRemainingAmount(0.0);
                            bnplEntity.setStatus(BNPLStatus.PAID);

                            bnplRepository.save(bnplEntity);
                        } else {
                            bnplEntity.setRemainingAmount(bnplEntity.getRemainingAmount() - repaidAmount);
                        }
                    }

                }
                else
                    throw new RuntimeException("");
            }
        }
        else{
            if (optionalUserEntity.isPresent()) {
                Optional<BNPLEntity> optionalBNPLEntity = bnplRepository.findByOrder_OrderId(userRequest.getOrderID());
                if(optionalBNPLEntity.isPresent()){
                    BNPLEntity bnplEntity = optionalBNPLEntity.get();
                    double remainingAmount = bnplEntity.getRemainingAmount();
                    if(remainingAmount != userRequest.getAmount())
                        throw new RuntimeException("Repayment amount not equals to remaining amount for particular order");

                    bnplEntity.setRemainingAmount(0.0);
                    bnplEntity.setStatus(BNPLStatus.PAID);
                    bnplRepository.save(bnplEntity);
                }
                else
                    throw new RuntimeException("No orderId present repayment");
            }
            else{
                throw new RuntimeException("No user present with this username");
            }
        }
    }
}