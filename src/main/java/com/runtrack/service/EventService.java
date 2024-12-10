package com.runtrack.service;

import com.runtrack.entity.Event;
import com.runtrack.entity.Host;
import com.runtrack.entity.User;
import com.runtrack.repository.EventRepository;
import com.runtrack.repository.HostRepository;
import com.runtrack.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final HostRepository hostRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository, HostRepository hostRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.hostRepository = hostRepository;
    }

    public Event createEvent(String city, String date, String userId) {
        String eventId = UUID.randomUUID().toString();
        Event event = new Event();
        event.setEventId(eventId);
        event.setCity(city);

        try {
            event.setDate(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Expected format: YYYY-MM-DD");
        }

        eventRepository.save(event);

        Host host = new Host(userId, eventId);
        hostRepository.save(host);

        return event;
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    public void deleteEventById(String eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        List<Host> hosts = hostRepository.findByEventId(eventId);
        hostRepository.deleteAll(hosts);
        eventRepository.deleteById(eventId);
    }

    public List<User> getEventUsers(String eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        List<Host> hosts = hostRepository.findByEventId(eventId);

        return hosts.stream()
                .map(host -> userRepository.findById(host.getUserId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
                .collect(Collectors.toList());
    }
}
