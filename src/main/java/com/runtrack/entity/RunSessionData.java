package com.runtrack.entity;

import java.time.LocalDateTime;

public class RunSessionData {
    private String runSessionId;
    private String userId;
    private Float sessionDistance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String eventId;

    // 全参数构造函数
    public RunSessionData(String runSessionId, String userId, Float sessionDistance, LocalDateTime startTime, LocalDateTime endTime, String eventId) {
        this.runSessionId = runSessionId;
        this.userId = userId;
        this.sessionDistance = sessionDistance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
    }

    // Getters and Setters
    public String getRunSessionId() {
        return runSessionId;
    }

    public void setRunSessionId(String runSessionId) {
        this.runSessionId = runSessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getSessionDistance() {
        return sessionDistance;
    }

    public void setSessionDistance(Float sessionDistance) {
        this.sessionDistance = sessionDistance;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
