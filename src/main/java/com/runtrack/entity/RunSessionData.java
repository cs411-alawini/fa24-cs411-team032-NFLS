package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "RunSessionData")
@Data
public class RunSessionData {
    @Id
    private String runSessionId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "SessionDistance")
    private Float sessionDistance;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "EventId")
    private String eventId;
}
