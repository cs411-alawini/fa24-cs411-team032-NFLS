package com.runtrack.service;

import com.runtrack.entity.RunSessionData;
import com.runtrack.repository.RunSessionDataRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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
}
