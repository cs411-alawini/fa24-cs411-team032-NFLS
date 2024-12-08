package com.runtrack.controller;

import com.runtrack.entity.Product;
import com.runtrack.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000") // 确保前端地址匹配
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 获取所有产品（支持分页）
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    // 创建新产品
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 根据名称模糊查询
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.findByProductName(name);
    }

    // 查询价格小于等于指定值的产品
    @GetMapping("/max-price")
    public List<Product> searchByMaxPrice(@RequestParam double price) {
        return productService.findProductsByMaxPrice(price);
    }

    // 动态查询产品（支持名称、价格范围）
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return productService.filterProducts(name, minPrice, maxPrice);
    }
}
