package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UserId")
    private String userId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "Host",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "EventId")
    )
    private Set<Event> hostedEvents = new HashSet<>();

    public void addHostedEvent(Event event) {
        this.hostedEvents.add(event);
        event.getHosts().add(this);
    }

    public void removeHostedEvent(Event event) {
        this.hostedEvents.remove(event);
        event.getHosts().remove(this);
    }

}
