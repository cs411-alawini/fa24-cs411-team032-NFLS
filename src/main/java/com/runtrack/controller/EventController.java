package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    // @GetMapping("/{eventId}/users")
    // public Set<User> getEventUsers(@PathVariable String eventId) {
    //     return eventService.getEventUsers(eventId); // 查询事件关联的用户
    // }

    @GetMapping("/location")
    public List<Event> getEventsByLocation(@RequestParam String location) {
        return eventService.getEventsByLocation(location);
    }


    @PostMapping("/{eventId}/users/{userId}")
    public Event addHostToEvent(@PathVariable String eventId, @PathVariable String userId) {
        return eventService.addHostToEvent(eventId, userId);
    }

    @DeleteMapping("/{eventId}/users/{userId}")
    public Event removeHostFromEvent(@PathVariable String eventId, @PathVariable String userId) {
        return eventService.removeHostFromEvent(eventId, userId);
    }
}
