//package com.runtrack.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "User")
//@Data
//@EqualsAndHashCode(exclude = "hostedEvents") // 排除 hostedEvents 防止循环依赖
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "UserId")
//    private String userId;
//
//    @Column(name = "FirstName", nullable = false)
//    private String firstName;
//
//    @Column(name = "LastName", nullable = false)
//    private String lastName;
//
//    @Column(name = "Email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "PhoneNumber", nullable = true)
//    private String phoneNumber;
//
//    @Column(name = "Password", nullable = false)
//    private String password;
//
//    @ManyToMany
//    @JoinTable(
//            name = "Host",
//            joinColumns = @JoinColumn(name = "UserId"),
//            inverseJoinColumns = @JoinColumn(name = "EventId")
//    )
//    @JsonBackReference // 防止循环引用
//    private Set<Event> hostedEvents = new HashSet<>();
//
//    public void addHostedEvent(Event event) {
//        this.hostedEvents.add(event);
//        event.getHosts().add(this);
//    }
//
//=    public void removeHostedEvent(Event event) {
//        this.hostedEvents.remove(event);
//        event.getHosts().remove(this);
//    }
//}

package com.runtrack.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Set<Event> hostedEvents = new HashSet<>();

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getHostedEvents() {
        return hostedEvents;
    }

    public void addHostedEvent(Event event) {
        this.hostedEvents.add(event);
    }

    public void removeHostedEvent(Event event) {
        this.hostedEvents.remove(event);
    }
}