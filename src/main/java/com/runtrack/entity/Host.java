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
    private UUID id;

    @Column("UserId")
    private UUID userId;

    @Column("EventId")
    private UUID eventId;

    public Host(UUID id, UUID userId, UUID eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }
}
