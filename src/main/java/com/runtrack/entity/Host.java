package com.runtrack.entity;

public class Host {

    private String userId;
    private String eventId;

    // 默认构造函数
    public Host() {}

    // 带参数的构造函数
    public Host(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    // Getters 和 Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
