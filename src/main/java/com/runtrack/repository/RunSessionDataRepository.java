package com.runtrack.repository;

import com.runtrack.entity.RunSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RunSessionDataRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RunSessionDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper 将数据库行映射为 RunSessionData 对象
    private static class RunSessionDataRowMapper implements RowMapper<RunSessionData> {
        @Override
        public RunSessionData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new RunSessionData(
                    rs.getString("runSessionId"),
                    rs.getString("UserId"),
                    rs.getFloat("SessionDistance"),
                    rs.getTimestamp("StartTime").toLocalDateTime(),
                    rs.getTimestamp("EndTime").toLocalDateTime(),
                    rs.getString("EventId")
            );
        }
    }

    public List<RunSessionData> findByUserId(String userId) {
        String sql = "SELECT * FROM RunSessionData WHERE UserId = ?";
        return jdbcTemplate.query(sql, new RunSessionDataRowMapper(), userId);
    }

    public List<RunSessionData> findByEventId(String eventId) {
        String sql = "SELECT * FROM RunSessionData WHERE EventId = ?";
        return jdbcTemplate.query(sql, new RunSessionDataRowMapper(), eventId);
    }

    public Float getTotalDistanceByUserId(String userId) {
        String sql = "SELECT SUM(SessionDistance) FROM RunSessionData WHERE UserId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Float.class);
    }

    public Float getTotalTimeByUserId(String userId) {
        String sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE, StartTime, EndTime)) FROM RunSessionData WHERE UserId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Float.class);
    }

    public List<RunSessionData> findByUserIdAndStartTimeBetween(String userId, LocalDateTime start, LocalDateTime end) {
        String sql = "SELECT * FROM RunSessionData WHERE UserId = ? AND StartTime BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new RunSessionDataRowMapper(), userId, Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    public int save(RunSessionData runSessionData) {
        String sql = "INSERT INTO RunSessionData (runSessionId, UserId, SessionDistance, StartTime, EndTime, EventId) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                runSessionData.getRunSessionId(),
                runSessionData.getUserId(),
                runSessionData.getSessionDistance(),
                Timestamp.valueOf(runSessionData.getStartTime()),
                Timestamp.valueOf(runSessionData.getEndTime()),
                runSessionData.getEventId()
        );
    }

    public int updateEndTime(String runSessionId, LocalDateTime endTime) {
        String sql = "UPDATE RunSessionData SET EndTime = ? WHERE runSessionId = ?";
        return jdbcTemplate.update(sql, Timestamp.valueOf(endTime), runSessionId);
    }
}