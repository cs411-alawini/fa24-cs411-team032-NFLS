package com.runtrack.controller;

import com.runtrack.entity.RunSessionData;
import com.runtrack.service.RunSessionDataService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/run-sessions")
public class RunSessionDataController {
    private final RunSessionDataService runSessionDataService;

    public RunSessionDataController(RunSessionDataService runSessionDataService) {
        this.runSessionDataService = runSessionDataService;
    }

    @GetMapping("/user/{userId}")
    public List<RunSessionData> getRunSessionsByUserId(@PathVariable String userId) {
        return runSessionDataService.getRunSessionsByUserId(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<RunSessionData> getRunSessionsByEventId(@PathVariable String eventId) {
        return runSessionDataService.getRunSessionsByEventId(eventId);
    }

    @GetMapping("/user/{userId}/time-range")
    public List<RunSessionData> getRunSessionsByUserIdAndTimeRange(
            @PathVariable String userId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return runSessionDataService.getRunSessionsByUserIdAndTimeRange(userId, startTime, endTime);
    }

    @GetMapping("/user/{userId}/all-stats")
    public Map<String, Object> getAllStats(@PathVariable String userId) {
        return runSessionDataService.getAllStats(userId);
    }


    @GetMapping("/user/{userId}/daily-distances")
    public Map<LocalDate, Double> getDailyDistances(@PathVariable String userId) {
        return runSessionDataService.getDailyDistances(userId);
    }

    @PostMapping
    public RunSessionData saveRunSession(@RequestBody RunSessionData runSessionData) {
        return runSessionDataService.saveRunSession(runSessionData);
    }
}
