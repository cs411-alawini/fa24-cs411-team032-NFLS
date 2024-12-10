package com.runtrack.controller;

import com.runtrack.entity.Host;
import com.runtrack.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    // 根据 UserId 获取所有 Host
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Host>> getHostsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(hostService.getHostsByUserId(userId));
    }

    // 根据 EventId 获取所有 Host
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Host>> getHostsByEventId(@PathVariable String eventId) {
        return ResponseEntity.ok(hostService.getHostsByEventId(eventId));
    }
}
