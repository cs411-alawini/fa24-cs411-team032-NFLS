//package com.runtrack.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "RunSessionData")
//@Data
//public class RunSessionData {
//    @Id
//    private String runSessionId;
//
//    @Column(name = "UserId")
//    private String userId;
//
//    @Column(name = "SessionDistance")
//    private Float sessionDistance;
//
//    @Column(name = "StartTime")
//    private LocalDateTime startTime;
//
//    @Column(name = "EndTime")
//    private LocalDateTime endTime;
//
//    @Column(name = "EventId")
//    private String eventId;
//}

package com.runtrack.entity;

import java.time.LocalDateTime;

public class RunSessionData {
    private String runSessionId;
    private String userId;
    private Float sessionDistance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
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
