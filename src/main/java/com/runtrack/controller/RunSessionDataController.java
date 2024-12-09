package com.runtrack.controller;

import com.runtrack.entity.RunSessionData;
import com.runtrack.service.RunSessionDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/runsessions")
public class RunSessionController {

    private final RunSessionDataService runSessionDataService;

    public RunSessionController(RunSessionDataService runSessionDataService) {
        this.runSessionDataService = runSessionDataService;
    }

    @GetMapping("/user/{userId}")
    public List<RunSessionData> getRunSessionsByUserId(@PathVariable String userId) {
        return runSessionDataService.getRunSessionsByUserId(userId);
    }

    @GetMapping("/stats/{userId}")
    public Map<String, Object> getAllStats(@PathVariable String userId) {
        return runSessionDataService.getAllStats(userId);
    }

    @PostMapping
    public RunSessionData saveRunSession(@RequestBody RunSessionData runSessionData) {
        return runSessionDataService.saveRunSession(runSessionData);
    }
}
