package com.runtrack.service;

import com.runtrack.entity.RunSessionData;
import com.runtrack.repository.RunSessionDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RunSessionDataService {
    private final RunSessionDataRepository runSessionDataRepository;

    public RunSessionDataService(RunSessionDataRepository runSessionDataRepository) {
        this.runSessionDataRepository = runSessionDataRepository;
    }

    public List<RunSessionData> getRunSessionsByUserId(String userId) {
        return runSessionDataRepository.findByUserId(userId);
    }

    public List<RunSessionData> getRunSessionsByEventId(String eventId) {
        return runSessionDataRepository.findByEventId(eventId);
    }

    public List<RunSessionData> getRunSessionsByUserIdAndTimeRange(String userId, LocalDateTime start, LocalDateTime end) {
        return runSessionDataRepository.findByUserIdAndStartTimeBetween(userId, start, end);
    }

    public RunSessionData saveRunSession(RunSessionData runSessionData) {
        return runSessionDataRepository.save(runSessionData);
    }
}
