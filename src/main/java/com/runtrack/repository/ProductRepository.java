package com.runtrack.repository;

import com.runtrack.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 保存产品
    public int save(Product product) {
        String sql = "INSERT INTO Product (ProductId, ProductName, ProductPrice) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice());
    }

    // 查询所有产品
    public List<Product> findAll() {
        String sql = "SELECT * FROM Product";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    public Optional<Product> findById(String productId) {
        String sql = "SELECT * FROM Product WHERE ProductId = ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{productId}, this::mapRowToProduct);
        return products.stream().findFirst();
    }


    // 根据产品名称（忽略大小写）查询产品
    public List<Product> findByProductNameContainingIgnoreCase(String productName) {
        String sql = "SELECT * FROM Product WHERE LOWER(ProductName) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, new Object[]{"%" + productName + "%"}, this::mapRowToProduct);
    }

    // 查询价格小于等于指定值的产品
    public List<Product> findByProductPriceLessThanEqual(double maxPrice) {
        String sql = "SELECT * FROM Product WHERE ProductPrice <= ?";
        return jdbcTemplate.query(sql, new Object[]{maxPrice}, this::mapRowToProduct);
    }

    // 查询价格在指定范围内的产品
    public List<Product> findByProductPriceBetween(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM Product WHERE ProductPrice BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{minPrice, maxPrice}, this::mapRowToProduct);
    }

    // 根据名称和价格范围查询产品
    public List<Product> findByProductNameContainingIgnoreCaseAndProductPriceBetween(String name, double minPrice, double maxPrice) {
        String sql = "SELECT * FROM Product WHERE LOWER(ProductName) LIKE LOWER(?) AND ProductPrice BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%", minPrice, maxPrice}, this::mapRowToProduct);
    }

    // 根据名称和最低价格查询产品
    public List<Product> findByProductNameContainingIgnoreCaseAndProductPriceGreaterThanEqual(String name, double minPrice) {
        String sql = "SELECT * FROM Product WHERE LOWER(ProductName) LIKE LOWER(?) AND ProductPrice >= ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%", minPrice}, this::mapRowToProduct);
    }

    // 根据名称和最高价格查询产品
    public List<Product> findByProductNameContainingIgnoreCaseAndProductPriceLessThanEqual(String name, double maxPrice) {
        String sql = "SELECT * FROM Product WHERE LOWER(ProductName) LIKE LOWER(?) AND ProductPrice <= ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%", maxPrice}, this::mapRowToProduct);
    }

    // 查询价格大于等于指定值的产品
    public List<Product> findByProductPriceGreaterThanEqual(double minPrice) {
        String sql = "SELECT * FROM Product WHERE ProductPrice >= ?";
        return jdbcTemplate.query(sql, new Object[]{minPrice}, this::mapRowToProduct);
    }

    // 辅助方法：将 ResultSet 映射为 Product 对象
    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getString("ProductId"));
        product.setProductName(rs.getString("ProductName"));
        product.setProductPrice(rs.getDouble("ProductPrice"));
        return product;
    }
}
