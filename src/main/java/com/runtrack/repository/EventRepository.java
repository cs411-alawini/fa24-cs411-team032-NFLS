package com.runtrack.repository;

import com.runtrack.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findByDate(LocalDate date); // Query events by date

    List<Event> findByCity(String city); // Query events by city
}
