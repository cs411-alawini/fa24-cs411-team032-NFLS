package com.runtrack.repository;

import com.runtrack.entity.Host;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HostRepository extends CrudRepository<Host, UUID> {

    @Query("SELECT * FROM Host WHERE UserId = :userId")
    List<Host> findByUserId(UUID userId);

    @Query("SELECT * FROM Host WHERE EventId = :eventId")
    List<Host> findByEventId(UUID eventId);
}
