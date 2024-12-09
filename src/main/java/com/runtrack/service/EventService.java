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

    // 创建事件
    public Event createEvent(String city, String date, UUID userId) {
        UUID eventId = UUID.randomUUID();
        Event event = new Event();
        event.setEventId(eventId);
        event.setCity(city);

        try {
            event.setDate(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Expected format: YYYY-MM-DD");
        }

        // 检查用户是否存在
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        // 保存事件
        eventRepository.save(event);

        // 创建 Host 关系并保存
        Host host = new Host(UUID.randomUUID(), userId, eventId);
        hostRepository.save(host);

        return event;
    }

    // 获取所有事件
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    // 根据 ID 获取事件
    public Event getEventById(UUID eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    // 根据日期获取事件
    public List<Event> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    // 根据城市获取事件
    public List<Event> getEventsByCity(String city) {
        return eventRepository.findByCity(city);
    }

    // 删除事件
    public void deleteEventById(UUID eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        // 删除关联的 Host 记录
        List<Host> hosts = hostRepository.findByEventId(eventId);
        hostRepository.deleteAll(hosts);

        // 删除事件
        eventRepository.deleteById(eventId);
    }

    // 获取事件的所有用户
    public List<User> getEventUsers(UUID eventId) {
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
