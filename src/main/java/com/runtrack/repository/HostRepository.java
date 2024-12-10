package com.runtrack.repository;

import com.runtrack.entity.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 保存 Host
    public int save(Host host) {
        String sql = "INSERT INTO Host (UserId, EventId) VALUES (?, ?)";
        return jdbcTemplate.update(sql, host.getUserId(), host.getEventId());
    }

    // 删除 Host
    public int delete(String userId, String eventId) {
        String sql = "DELETE FROM Host WHERE UserId = ? AND EventId = ?";
        return jdbcTemplate.update(sql, userId, eventId);
    }

    // 根据 UserId 查询所有 Host
    public List<Host> findByUserId(String userId) {
        String sql = "SELECT * FROM Host WHERE UserId = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, this::mapRowToHost);
    }

    // 根据 EventId 查询所有 Host
    public List<Host> findByEventId(String eventId) {
        String sql = "SELECT * FROM Host WHERE EventId = ?";
        return jdbcTemplate.query(sql, new Object[]{eventId}, this::mapRowToHost);
    }

    // 检查 Host 是否存在
    public boolean existsById(String userId, String eventId) {
        String sql = "SELECT COUNT(*) FROM Host WHERE UserId = ? AND EventId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, eventId}, Integer.class);
        return count != null && count > 0;
    }

    public void deleteAll(List<Host> hosts) {
        String sql = "DELETE FROM Host WHERE UserId = ? AND EventId = ?";
        for (Host host : hosts) {
            jdbcTemplate.update(sql, host.getUserId(), host.getEventId());
        }
    }


    // 将 ResultSet 映射为 Host 对象
    private Host mapRowToHost(ResultSet rs, int rowNum) throws SQLException {
        return new Host(rs.getString("UserId"), rs.getString("EventId"));
    }
}
