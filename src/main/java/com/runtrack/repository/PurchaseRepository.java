package com.runtrack.repository;

import com.runtrack.entity.Purchase;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, String> {

    @Query("SELECT * FROM Purchase WHERE UserId = :userId")
    List<Purchase> findByUserId(@Param("userId") String userId);

    @Query("SELECT * FROM Purchase WHERE ProductId = :productId")
    List<Purchase> findByProductId(@Param("productId") String productId);
}
