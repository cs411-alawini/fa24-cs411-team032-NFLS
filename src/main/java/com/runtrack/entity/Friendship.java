//package com.runtrack.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "MakeFriends")
//@Data
//public class Friendship {
//    @Id
//    @Column(name = "FriendshipId", nullable = false)
//    private String friendshipId;
//
//    @Column(name = "UserId", nullable = false)
//    private String userId;
//
//    @Column(name = "FriendUserId", nullable = false)
//    private String friendUserId;
//
//    @Column(name = "StartDate")
//    private LocalDate startDate;
//
//    @Column(name = "FriendshipLevel")
//    private String friendshipLevel;
//}

package com.runtrack.entity;

import java.time.LocalDate;

public class Friendship {
    private String friendshipId;
    private String userId;
    private String friendUserId;
    private LocalDate startDate;
    private String friendshipLevel;

    // Getters and Setters
    public String getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(String friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(String friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
