package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "hostedEvents")
    private Set<User> hosts = new HashSet<>();

    public void addHost(User user) {
        this.hosts.add(user);
        user.getHostedEvents().add(this);
    }

    public void removeHost(User user) {
        this.hosts.remove(user);
        user.getHostedEvents().remove(this);
    }
}
