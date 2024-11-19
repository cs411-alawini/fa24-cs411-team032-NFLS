package com.runtrack.repository;

import com.runtrack.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    List<Purchase> findByUserId(String userId); // 根据用户ID查询购买记录

    List<Purchase> findByProductId(String productId); // 根据产品ID查询购买记录
}
