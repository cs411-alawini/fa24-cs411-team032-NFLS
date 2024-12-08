package com.runtrack.repository;

import com.runtrack.entity.RunSessionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RunSessionDataRepository extends JpaRepository<RunSessionData, String> {
    // 根据 UserId 查询用户的所有跑步数据
    List<RunSessionData> findByUserId(String userId);

    // 根据 EventId 查询事件的所有跑步数据
    List<RunSessionData> findByEventId(String eventId);

    // 根据用户 ID 和时间范围查询跑步数据
    List<RunSessionData> findByUserIdAndStartTimeBetween(String userId, LocalDateTime start, LocalDateTime end);
}
