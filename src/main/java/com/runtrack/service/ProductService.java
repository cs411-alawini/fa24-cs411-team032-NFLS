package com.runtrack.service;

import com.runtrack.entity.Product;
import com.runtrack.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 获取所有产品（支持分页）
    public List<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    // 保存产品
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // 根据产品名称查询
    public List<Product> findByProductName(String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }

    // 查询价格小于等于指定值的产品
    public List<Product> findProductsByMaxPrice(double maxPrice) {
        return productRepository.findByProductPriceLessThanEqual(maxPrice);
    }

    // 动态条件查询（名称和价格范围）
    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice) {
        if (name != null && minPrice != null && maxPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceBetween(
                    name, minPrice, maxPrice
            );
        } else if (name != null && minPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceGreaterThanEqual(
                    name, minPrice
            );
        } else if (name != null && maxPrice != null) {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductPriceLessThanEqual(
                    name, maxPrice
            );
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByProductPriceBetween(minPrice, maxPrice);
        } else if (name != null) {
            return productRepository.findByProductNameContainingIgnoreCase(name);
        } else if (minPrice != null) {
            return productRepository.findByProductPriceGreaterThanEqual(minPrice);
        } else if (maxPrice != null) {
            return productRepository.findByProductPriceLessThanEqual(maxPrice);
        } else {
            return productRepository.findAll(); // 返回所有产品
        }
    }
}
