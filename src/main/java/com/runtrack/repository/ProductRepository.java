package com.runtrack.repository;

import com.runtrack.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // 根据产品名称查询
    List<Product> findByProductName(String productName);

    // 查询价格小于等于指定值的产品
    List<Product> findByProductPriceLessThanEqual(double price);
}
