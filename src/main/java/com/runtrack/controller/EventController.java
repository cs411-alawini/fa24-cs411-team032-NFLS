package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable String eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/date")
    public List<Event> getEventsByDate(@RequestParam LocalDate date) {
        return eventService.getEventsByDate(date);
    }

    @GetMapping("/location")
    public List<Event> getEventsByLocation(@RequestParam String location) {
        return eventService.getEventsByLocation(location);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable String eventId) {
        eventService.deleteEventById(eventId);
    }
}
