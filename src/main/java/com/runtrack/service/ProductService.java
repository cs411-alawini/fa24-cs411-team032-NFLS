package com.runtrack.service;

import com.runtrack.entity.Product;
import com.runtrack.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterable.forEach(productList::add);
        return productList;
    }

    public Product saveProduct(Product product) {
        int rowsAffected = productRepository.save(product);
        if (rowsAffected > 0) {
            // 假设 product.getProductId() 可以获取到产品的唯一 ID
            return productRepository.findById(product.getProductId())
                    .orElseThrow(() -> new RuntimeException("Failed to retrieve saved product"));
        } else {
            throw new RuntimeException("Failed to save product");
        }
    }


    public List<Product> findByProductName(String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }

    public List<Product> findProductsByMaxPrice(double maxPrice) {
        return productRepository.findByProductPriceLessThanEqual(maxPrice);
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice) {
        if (name != null && minPrice != null && maxPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceBetween(name, minPrice, maxPrice);
        } else if (name != null && minPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceGreaterThanEqual(name, minPrice);
        } else if (name != null && maxPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceLessThanEqual(name, maxPrice);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByProductPriceBetween(minPrice, maxPrice);
        } else if (name != null) {
            return productRepository.findByProductNameContainingIgnoreCase(name);
        } else if (minPrice != null) {
            return productRepository.findByProductPriceGreaterThanEqual(minPrice);
        } else if (maxPrice != null) {
            return productRepository.findByProductPriceLessThanEqual(maxPrice);
        } else {
            Iterable<Product> iterable = productRepository.findAll();
            List<Product> productList = new ArrayList<>();
            iterable.forEach(productList::add);
            return productList;
        }
    }
}
