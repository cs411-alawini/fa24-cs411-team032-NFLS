package com.runtrack.repository;

import com.runtrack.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 保存用户
    public int save(User user) {
        String sql = "INSERT INTO User (UserId, FirstName, LastName, Email, PhoneNumber, Password) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword());
    }

    // 根据 ID 查找用户
    public Optional<User> findById(String userId) {
        String sql = "SELECT * FROM User WHERE UserId = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{userId}, this::mapRowToUser);
        return users.stream().findFirst();
    }

    // 根据邮箱查找用户
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM User WHERE Email = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, this::mapRowToUser);
        return users.stream().findFirst();
    }

    // 删除用户
    public int deleteById(String userId) {
        String sql = "DELETE FROM User WHERE UserId = ?";
        return jdbcTemplate.update(sql, userId);
    }

    // 用户是否存在
    public boolean existsById(String userId) {
        String sql = "SELECT COUNT(*) FROM User WHERE UserId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count != null && count > 0;
    }

    // 根据多个 ID 查找用户
    public List<User> findAllById(Set<String> userIds) {
        String sql = "SELECT * FROM User WHERE UserId IN (" + String.join(",", userIds.stream().map(id -> "'" + id + "'").toArray(String[]::new)) + ")";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    // 辅助方法：将 ResultSet 映射为 User 对象
    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString("UserId"));
        user.setFirstName(rs.getString("FirstName"));
        user.setLastName(rs.getString("LastName"));
        user.setEmail(rs.getString("Email"));
        user.setPhoneNumber(rs.getString("PhoneNumber"));
        user.setPassword(rs.getString("Password"));
        return user;
    }
}
