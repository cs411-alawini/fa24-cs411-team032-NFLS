package com.runtrack.controller;

import com.runtrack.entity.RunSessionData;
import com.runtrack.service.RunSessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/run-sessions")
public class RunSessionDataController {

    @Autowired
    private RunSessionDataService service;

    @GetMapping("/user/{userId}")
    public List<RunSessionData> getRunSessionsByUserId(@PathVariable String userId) {
        return service.getRunSessionsByUserId(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<RunSessionData> getRunSessionsByEventId(@PathVariable String eventId) {
        return service.getRunSessionsByEventId(eventId);
    }


    @GetMapping("/user/{userId}/time-range")
    public List<RunSessionData> getRunSessionsByUserIdAndTimeRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return service.getRunSessionsByUserIdAndTimeRange(userId, start, end);
    }

    @PutMapping("/{runSessionId}/stop")
    public ResponseEntity<String> stopRunSession(@PathVariable String runSessionId) {
        try {
            service.stopRunSession(runSessionId);
            return ResponseEntity.ok("Run session stopped successfully.");
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Error stopping run session: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> saveRunSession(@RequestBody RunSessionData runSessionData) {
        service.saveRunSession(runSessionData);
        return ResponseEntity.ok("Run session saved successfully.");
    }
}
