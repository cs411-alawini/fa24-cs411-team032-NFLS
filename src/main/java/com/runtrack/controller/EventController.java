package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        logger.info("Fetching all events");
        return eventService.getAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Map<String, String> payload) {
        logger.info("Received payload: {}", payload);

        String city = payload.get("city");
        String date = payload.get("date");
        String userId = payload.get("userId");

        if (city == null || date == null || userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City, date, and userId are required.");
        }

        return eventService.createEvent(city, date, userId);
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable String eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/date")
    public List<Event> getEventsByDate(@RequestParam LocalDate date) {
        return eventService.getEventsByDate(date);
    }

    @GetMapping("/city")
    public List<Event> getEventsByCity(@RequestParam String city) {
        return eventService.getEventsByCity(city);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable String eventId) {
        eventService.deleteEventById(eventId);
    }

    @GetMapping("/{eventId}/users")
    public Set<User> getEventUsers(@PathVariable String eventId) {
        return eventService.getEventUsers(eventId);
    }
}
