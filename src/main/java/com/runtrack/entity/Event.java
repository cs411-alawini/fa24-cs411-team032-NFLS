package com.runtrack.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table("Event")
public class Event {

    @Id
    @Column("EventId")
    private String eventId;

    @Column("City")
    private String city;

    @Column("Date")
    private LocalDate date;

    @Column("Location")
    private String location;

    // Constructors, getters, and setters will be handled by Lombok's @Data
}

