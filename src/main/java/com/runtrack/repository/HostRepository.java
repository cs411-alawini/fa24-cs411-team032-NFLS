package com.runtrack.repository;

import com.runtrack.entity.Host;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HostRepository extends CrudRepository<Host, UUID> {
    List<Host> findByUserId(UUID userId);
    List<Host> findByEventId(UUID eventId);
}
