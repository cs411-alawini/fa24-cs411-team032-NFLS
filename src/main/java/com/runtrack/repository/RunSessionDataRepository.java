package com.runtrack.repository;

import com.runtrack.entity.RunSessionData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RunSessionDataRepository extends CrudRepository<RunSessionData, String> {
    List<RunSessionData> findByUserId(String userId);

    List<RunSessionData> findByEventId(String eventId);

    List<RunSessionData> findByUserIdAndStartTimeBetween(String userId, LocalDateTime start, LocalDateTime end);
}
