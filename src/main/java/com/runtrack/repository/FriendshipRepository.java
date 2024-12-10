package com.runtrack.repository;

import com.runtrack.entity.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class FriendshipRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendshipRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper 将数据库行映射为 Friendship 对象
    private static class FriendshipRowMapper implements RowMapper<Friendship> {
        @Override
        public Friendship mapRow(ResultSet rs, int rowNum) throws SQLException {
            Friendship friendship = new Friendship();
            friendship.setFriendshipId(rs.getString("FriendshipId"));
            friendship.setUserId(rs.getString("UserId"));
            friendship.setFriendUserId(rs.getString("FriendUserId"));
            friendship.setStartDate(rs.getDate("StartDate").toLocalDate());
            friendship.setFriendshipLevel(rs.getString("FriendshipLevel"));
            return friendship;
        }
    }

    // 查找指定用户的所有好友关系
    public List<Friendship> findAllFriendshipsByUserId(String userId) {
        String sql = "SELECT * FROM MakeFriends WHERE UserId = ? OR FriendUserId = ?";
        return jdbcTemplate.query(sql, new FriendshipRowMapper(), userId, userId);
    }

    // 保存新的好友关系
    public int save(Friendship friendship) {
        String sql = "INSERT INTO MakeFriends (FriendshipId, UserId, FriendUserId, StartDate, FriendshipLevel) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                friendship.getFriendshipId(),
                friendship.getUserId(),
                friendship.getFriendUserId(),
                Date.valueOf(friendship.getStartDate()),
                friendship.getFriendshipLevel()
        );
    }

    // 检查指定的用户ID是否存在于好友关系中
    public boolean existsById(String userId) {
        String sql = "SELECT COUNT(*) FROM MakeFriends WHERE UserId = ? OR FriendUserId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, userId);
        return count != null && count > 0;
    }
}
