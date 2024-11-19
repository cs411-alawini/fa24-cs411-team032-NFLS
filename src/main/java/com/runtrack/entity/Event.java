package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Event")
@Data
public class Event {
    @Id
    @Column(name = "EventID", nullable = false)
    private String eventId;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Location")
    private String location;
}
