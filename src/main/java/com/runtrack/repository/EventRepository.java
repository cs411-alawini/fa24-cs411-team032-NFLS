package com.runtrack.repository;

import com.runtrack.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 保存事件
    public int save(Event event) {
        String sql = "INSERT INTO Event (EventId, City, Date, Location) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                event.getEventId(),
                event.getCity(),
                event.getDate(),
                event.getLocation());
    }

    // 根据 ID 查找事件
    public Optional<Event> findById(String eventId) {
        String sql = "SELECT * FROM Event WHERE EventId = ?";
        List<Event> events = jdbcTemplate.query(sql, new Object[]{eventId}, this::mapRowToEvent);
        return events.stream().findFirst();
    }

    // 获取所有事件
    public List<Event> findAll() {
        String sql = "SELECT * FROM Event";
        return jdbcTemplate.query(sql, this::mapRowToEvent);
    }

    // 根据位置查找事件（忽略大小写）
    public List<Event> findByLocationIgnoreCase(String location) {
        String sql = "SELECT * FROM Event WHERE LOWER(Location) = LOWER(?)";
        return jdbcTemplate.query(sql, new Object[]{location}, this::mapRowToEvent);
    }

    // 根据日期查找事件
    public List<Event> findByDate(LocalDate date) {
        String sql = "SELECT * FROM Event WHERE Date = ?";
        return jdbcTemplate.query(sql, new Object[]{date}, this::mapRowToEvent);
    }

    // 删除事件
    public int deleteById(String eventId) {
        String sql = "DELETE FROM Event WHERE EventId = ?";
        return jdbcTemplate.update(sql, eventId);
    }

    // 事件是否存在
    public boolean existsById(String eventId) {
        String sql = "SELECT COUNT(*) FROM Event WHERE EventId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{eventId}, Integer.class);
        return count != null && count > 0;
    }

    public List<Event> findByCity(String city) {
        String sql = "SELECT * FROM Event WHERE City = ?";
        return jdbcTemplate.query(sql, this::mapRowToEvent, city);
    }


    // 辅助方法：将 ResultSet 映射为 Event 对象
    private Event mapRowToEvent(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getString("EventId"));
        event.setCity(rs.getString("City"));
        event.setDate(rs.getDate("Date").toLocalDate());
        event.setLocation(rs.getString("Location"));
        return event;
    }
}
