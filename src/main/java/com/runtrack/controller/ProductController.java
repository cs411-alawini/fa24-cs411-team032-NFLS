package com.runtrack.controller;

import com.runtrack.entity.Product;
import com.runtrack.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.findByProductName(name);
    }

    @GetMapping("/max-price")
    public List<Product> searchByMaxPrice(@RequestParam double price) {
        return productService.findProductsByMaxPrice(price);
    }
}
