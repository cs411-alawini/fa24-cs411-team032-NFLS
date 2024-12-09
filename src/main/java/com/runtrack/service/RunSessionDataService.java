package com.runtrack.service;

import com.runtrack.entity.RunSessionData;
import com.runtrack.repository.RunSessionDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RunSessionDataService {
    private final RunSessionDataRepository runSessionDataRepository;

    public RunSessionDataService(RunSessionDataRepository runSessionDataRepository) {
        this.runSessionDataRepository = runSessionDataRepository;
    }

    public List<RunSessionData> getRunSessionsByUserId(String userId) {
        return (List<RunSessionData>) runSessionDataRepository.findByUserId(userId);
    }

    public List<RunSessionData> getRunSessionsByEventId(String eventId) {
        return (List<RunSessionData>) runSessionDataRepository.findByEventId(eventId);
    }

    public List<RunSessionData> getRunSessionsByUserIdAndTimeRange(String userId, LocalDateTime start, LocalDateTime end) {
        return runSessionDataRepository.findByUserIdAndStartTimeBetween(userId, start, end);
    }

    public Map<String, Object> getAllStats(String userId) {
        List<RunSessionData> sessions = getRunSessionsByUserId(userId);

        double totalDistance = sessions.stream()
                .mapToDouble(RunSessionData::getSessionDistance)
                .sum();

        long totalDuration = sessions.stream()
                .mapToLong(session -> ChronoUnit.SECONDS.between(session.getStartTime(), session.getEndTime()))
                .sum();

        double totalCalories = totalDistance * 60;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDistance", totalDistance);
        stats.put("totalTime", totalDuration / 3600.0);
        stats.put("totalCalories", totalCalories);

        return stats;
    }

    public Map<LocalDate, Double> getDailyDistances(String userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(6);

        List<RunSessionData> sessions = runSessionDataRepository.findByUserIdAndStartTimeBetween(
                userId,
                startOfWeek.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        return sessions.stream()
                .collect(Collectors.groupingBy(
                        session -> session.getStartTime().toLocalDate(),
                        Collectors.summingDouble(RunSessionData::getSessionDistance)
                ));
    }

    public RunSessionData saveRunSession(RunSessionData runSessionData) {
        return runSessionDataRepository.save(runSessionData);
    }
}
