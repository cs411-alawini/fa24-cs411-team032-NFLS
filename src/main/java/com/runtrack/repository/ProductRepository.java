package com.runtrack.repository;

import com.runtrack.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String name);
    List<Product> findByProductPriceLessThanEqual(double maxPrice);
    List<Product> findByProductPriceBetween(double minPrice, double maxPrice);
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceBetween(String name, double minPrice, double maxPrice);
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceGreaterThanEqual(String name, double minPrice);
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceLessThanEqual(String name, double maxPrice);
    List<Product> findByProductPriceGreaterThanEqual(double minPrice);
}
