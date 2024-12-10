package com.runtrack.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table("Host")
public class Host {

    @Id
    private String id;

    @Column("UserId")
    private String userId;

    @Column("EventId")
    private String eventId;

    public Host(String id, String userId, String eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }
}
