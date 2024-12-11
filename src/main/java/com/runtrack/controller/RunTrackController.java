package com.runtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.runtrack.service.RunTrackService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/runtrack")
public class RunTrackController {

	private static final Logger logger = LoggerFactory.getLogger(RunTrackController.class);

    @Autowired
    private RunTrackService runTrackService;

    @GetMapping("/top-runners")
    public Map<String, Object> getTopRunners(
            @RequestParam String userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {

		logger.info("Received request for top runners with userId: {}, startDate: {}, endDate: {}", userId, startDate, endDate);

        try {
            return runTrackService.getTopRunnersAndProducts(userId, startDate, endDate);
        } catch (Exception e) {
			logger.error("Error fetching top runners and products for userId: {}, startDate: {}, endDate: {}", userId, startDate, endDate, e);
            // Handle exceptions appropriately
            throw new RuntimeException("Error fetching top runners and products", e);
        }
    }
}