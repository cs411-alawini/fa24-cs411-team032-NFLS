package com.runtrack.service;

import com.runtrack.entity.User;
import com.runtrack.entity.Event;
import com.runtrack.entity.Host;
import com.runtrack.repository.UserRepository;
import com.runtrack.repository.EventRepository;
import com.runtrack.repository.HostRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final HostRepository hostRepository;

    public UserService(UserRepository userRepository, EventRepository eventRepository, HostRepository hostRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.hostRepository = hostRepository;
    }

    // 用户注册
    public User registerUser(User user) {
        user.setUserId(UUID.randomUUID());
        return userRepository.save(user);
    }

    // 用户登录
    public Optional<User> loginUser(String email, String password) {
        return userRepository.findById(UUID.fromString(email))
                .filter(user -> user.getPassword().equals(password));
    }

    // 根据 ID 查找用户
    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    // 更新用户信息
    public User updateUser(UUID userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 删除用户
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    // 获取用户参加的所有事件
    public List<Event> getUserEvents(UUID userId) {
        return hostRepository.findByUserId(userId).stream()
                .map(host -> eventRepository.findById(host.getEventId()).orElse(null))
                .filter(event -> event != null)
                .collect(Collectors.toList());
    }

    // 给用户添加事件
    @Transactional
    public void addEventToUser(UUID userId, UUID eventId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException("Event not found");
        }

        Host host = new Host(UUID.randomUUID(), userId, eventId);
        hostRepository.save(host);
    }

    // 移除用户的事件
    @Transactional
    public void removeEventFromUser(UUID userId, UUID eventId) {
        hostRepository.findByUserId(userId).stream()
                .filter(host -> host.getEventId().equals(eventId))
                .findFirst()
                .ifPresent(hostRepository::delete);
    }
}
