package com.runtrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("MakeFriends")
public class Friendship {

    @Id
    @Column("FriendshipId")
    private UUID friendshipId;

    @Column("UserId")
    private UUID userId;

    @Column("FriendUserId")
    private UUID friendUserId;

    @Column("StartDate")
    private LocalDate startDate;

    @Column("FriendshipLevel")
    private String friendshipLevel;

    // 构造函数
    public Friendship() {
    }

    public Friendship(UUID friendshipId, UUID userId, UUID friendUserId, LocalDate startDate, String friendshipLevel) {
        this.friendshipId = friendshipId;
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.startDate = startDate;
        this.friendshipLevel = friendshipLevel;
    }

    // Getters 和 Setters

    public UUID getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(UUID friendshipId) {
        this.friendshipId = friendshipId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(UUID friendUserId) {
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
                "friendshipId=" + friendshipId +
                ", userId=" + userId +
                ", friendUserId=" + friendUserId +
                ", startDate=" + startDate +
                ", friendshipLevel='" + friendshipLevel + '\'' +
                '}';
    }
}
