package com.runtrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("RunSessionData")
public class RunSessionData {
    @Id
    private String runSessionId;

    @Column("UserId")
    private String userId;

    @Column("SessionDistance")
    private Float sessionDistance;

    @Column("StartTime")
    private LocalDateTime startTime;

    @Column("EndTime")
    private LocalDateTime endTime;

    @Column("EventId")
    private String eventId;

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
