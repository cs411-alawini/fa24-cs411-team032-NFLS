package com.runtrack.controller;

import com.runtrack.entity.RunSessionData;
import com.runtrack.service.RunSessionDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/runsessions")
public class RunSessionDataController { 

    private final RunSessionDataService runSessionDataService;

    public RunSessionDataController(RunSessionDataService runSessionDataService) {
        this.runSessionDataService = runSessionDataService;
    }

    @GetMapping("/user/{userId}")
    public List<RunSessionData> getRunSessionsByUserId(@PathVariable("userId") String userId) { // Explicit mapping for clarity
        return runSessionDataService.getRunSessionsByUserId(userId);
    }

    @GetMapping("/stats/{userId}")
    public Map<String, Object> getAllStats(@PathVariable("userId") String userId) { // Explicit mapping for clarity
        return runSessionDataService.getAllStats(userId);
    }

    @PostMapping
    public RunSessionData saveRunSession(@RequestBody RunSessionData runSessionData) {
        return runSessionDataService.saveRunSession(runSessionData);
    }
}
