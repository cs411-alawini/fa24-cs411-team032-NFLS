package com.runtrack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "MakeFriends")
@Data
public class Friendship {
    @Id
    @Column(name = "FriendshipId", nullable = false)
    private String friendshipId;

    @Column(name = "UserId", nullable = false)
    private String userId;

    @Column(name = "FriendUserId", nullable = false)
    private String friendUserId;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "FriendshipLevel")
    private String friendshipLevel;
}
