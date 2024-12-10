package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 获取所有事件
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        logger.info("Fetching all events");
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // 创建事件
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest payload) {
        logger.info("Received payload: {}", payload);

        try {
            Event event = eventService.createEvent(payload.getCity(), payload.getDate(), payload.getUserId());
            return ResponseEntity.ok(event);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Expected format: YYYY-MM-DD");
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    // 根据 ID 获取事件
    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    // 根据日期获取事件
//    @GetMapping("/date")
//    public ResponseEntity<List<Event>> getEventsByDate(@RequestParam String date) {
//        try {
//            LocalDate localDate = LocalDate.parse(date);
//            return ResponseEntity.ok(eventService.getEventsByDate(localDate));
//        } catch (DateTimeParseException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Expected format: YYYY-MM-DD");
//        }
//    }
//
//    @GetMapping("/city")
//    public ResponseEntity<List<Event>> getEventsByCity(@RequestParam String city) {
//        return ResponseEntity.ok(eventService.getEventsByCity(city));
//    }

    // 删除事件
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{eventId}/users")
    public ResponseEntity<List<User>> getEventUsers(@PathVariable String eventId) {
        return ResponseEntity.ok(eventService.getEventUsers(eventId));
    }

    // 内部类用于接收创建事件的请求体
    public static class EventRequest {
        private String city;
        private String date;
        private String userId; // 修改为 String

        // Getters and Setters
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "EventRequest{" +
                    "city='" + city + '\'' +
                    ", date='" + date + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }
}
