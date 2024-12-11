package com.runtrack.repository;

import com.runtrack.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper 将数据库行映射为 Purchase 对象
    private static class PurchaseRowMapper implements RowMapper<Purchase> {
        @Override
        public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Purchase(
                    rs.getString("PurchaseId"),
                    rs.getBigDecimal("PurchasePrice"),
                    rs.getInt("Quantity"),
                    rs.getString("UserId"),
                    rs.getString("ProductId")
            );
        }
    }

    // 查找所有购买记录
    public List<Purchase> findAll() {
        String sql = "SELECT * FROM Purchase";
        return jdbcTemplate.query(sql, new PurchaseRowMapper());
    }

    // 根据用户ID查找购买记录
    public List<Purchase> findByUserId(String userId) {
        String sql = "SELECT * FROM Purchase WHERE UserId = ?";
        return jdbcTemplate.query(sql, new PurchaseRowMapper(), userId);
    }

    // 根据产品ID查找购买记录
    public List<Purchase> findByProductId(String productId) {
        String sql = "SELECT * FROM Purchase WHERE ProductId = ?";
        return jdbcTemplate.query(sql, new PurchaseRowMapper(), productId);
    }

    // 保存新的购买记录
    public int save(Purchase purchase) {
        String sql = "INSERT INTO Purchase (PurchaseId, PurchasePrice, Quantity, UserId, ProductId) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                purchase.getPurchaseId(),
                purchase.getPurchasePrice(),
                purchase.getQuantity(),
                purchase.getUserId(),
                purchase.getProductId()
        );
    }

    // 根据购买ID删除记录
    public int deleteById(String purchaseId) {
        String sql = "DELETE FROM Purchase WHERE PurchaseId = ?";
        return jdbcTemplate.update(sql, purchaseId);
    }
}
