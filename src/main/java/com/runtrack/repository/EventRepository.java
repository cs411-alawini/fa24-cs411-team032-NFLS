package com.runtrack.repository;

import com.runtrack.entity.Event;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

    // Case-insensitive search for events by location
    @Query("SELECT * FROM Event WHERE LOWER(Location) = LOWER(:location)")
    List<Event> findByLocationIgnoreCase(String location);

    // Existing query remains unchanged
    @Query("SELECT * FROM Event WHERE Date = :date")
    List<Event> findByDate(LocalDate date);
}


