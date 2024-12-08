package com.runtrack.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Event")
@Data
@EqualsAndHashCode(exclude = "hosts")
public class Event {
    @Id
    @Column(name = "EventID", nullable = false, unique = true)
    private String eventId;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @ManyToMany(mappedBy = "hostedEvents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // Break cyclic reference here
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
