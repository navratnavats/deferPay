package com.github.navratna.deferpay.repositories;

import com.github.navratna.deferpay.models.BNPLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BNPLRepository extends JpaRepository<BNPLEntity, Long> {

    Optional<List<BNPLEntity>> findAllByUser_Username(String username);
    Optional<BNPLEntity> findByOrder_OrderId(Long orderId);
}
