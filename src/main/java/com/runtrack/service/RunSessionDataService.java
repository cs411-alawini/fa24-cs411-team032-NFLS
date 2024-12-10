package com.runtrack.service;

import com.runtrack.entity.RunSessionData;
import com.runtrack.repository.RunSessionDataRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RunSessionDataService {

    private final RunSessionDataRepository repository;

    public RunSessionDataService(RunSessionDataRepository repository) {
        this.repository = repository;
    }

    public List<RunSessionData> getRunSessionsByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public List<RunSessionData> getRunSessionsByEventId(String eventId) {
        return repository.findByEventId(eventId);
    }

    public List<RunSessionData> getRunSessionsByUserIdAndTimeRange(String userId, LocalDateTime start, LocalDateTime end) {
        return repository.findByUserIdAndStartTimeBetween(userId, start, end);
    }

    public void stopRunSession(String runSessionId) throws SQLException {
        repository.updateEndTime(runSessionId, LocalDateTime.now());
    }

    public void saveRunSession(RunSessionData runSessionData) {
        repository.save(runSessionData);
    }

    public Map<String, Object> getUserStats(String userId) {
        Float totalDistance = repository.getTotalDistanceByUserId(userId);
        Float totalTimeInMinutes = repository.getTotalTimeByUserId(userId);

        float caloriesBurned = 0;
        if (totalDistance != null && totalTimeInMinutes != null) {
            caloriesBurned = totalDistance * 60 + totalTimeInMinutes * 5;
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDistance", totalDistance != null ? totalDistance : 0);
        stats.put("totalTime", totalTimeInMinutes != null ? totalTimeInMinutes / 60 : 0); // 转换为小时
        stats.put("caloriesBurned", caloriesBurned);

        return stats;
    }
}
