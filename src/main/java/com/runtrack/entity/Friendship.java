package com.runtrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("MakeFriends")
public class Friendship {

    @Id
    @Column("FriendshipId")
    private String friendshipId;

    @Column("UserId")
    private String userId;

    @Column("FriendUserId")
    private String friendUserId;

    @Column("StartDate")
    private LocalDate startDate;

    @Column("FriendshipLevel")
    private String friendshipLevel;

    // 构造函数
    public Friendship() {
    }

    public Friendship(String friendshipId, String userId, String friendUserId, LocalDate startDate, String friendshipLevel) {
        this.friendshipId = friendshipId;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.startDate = startDate;
        this.friendshipLevel = friendshipLevel;
    }

    // Getters 和 Setters

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

    @Override
    public String toString() {
        return "Friendship{" +
                "friendshipId='" + friendshipId + '\'' +
                ", userId='" + userId + '\'' +
                ", friendUserId='" + friendUserId + '\'' +
                ", startDate=" + startDate +
                ", friendshipLevel='" + friendshipLevel + '\'' +
                '}';
    }
}
