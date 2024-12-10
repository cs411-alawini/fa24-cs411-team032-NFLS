package com.runtrack.service;

import com.runtrack.entity.User;
import com.runtrack.entity.Event;
import com.runtrack.entity.Host;
import com.runtrack.repository.UserRepository;
import com.runtrack.repository.EventRepository;
import com.runtrack.repository.HostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    // 注册用户
    public User registerUser(User user) {
        user.setUserId(UUID.randomUUID().toString()); // 生成 String 类型的 UUID
        userRepository.save(user);
        return user;
    }

    // 用户登录
    public Optional<User> loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword() != null && user.getPassword().equals(password));
    }

    // 根据 ID 查找用户
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 更新用户信息
    public User updateUser(String userId, User updatedUser) {
        updatedUser.setUserId(userId);
        int rowsAffected = userRepository.update(updatedUser);

        if (rowsAffected == 0) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        return updatedUser;
    }


    // 删除用户
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // 获取用户的所有事件
    public List<Event> getUserEvents(String userId) {
        return hostRepository.findByUserId(userId).stream()
                .map(host -> eventRepository.findById(host.getEventId()).orElse(null))
                .filter(event -> event != null)
                .collect(Collectors.toList());
    }

    // 为用户添加事件
    @Transactional
    public void addEventToUser(String userId, String eventId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException("Event not found");
        }

        Host host = new Host(userId, eventId);
        hostRepository.save(host);
    }

    // 删除用户的事件
    @Transactional
    public void removeEventFromUser(String userId, String eventId) {
        hostRepository.findByUserId(userId).stream()
                .filter(host -> host.getEventId().equals(eventId))
                .findFirst()
                .ifPresent(host -> hostRepository.delete(host.getUserId(), host.getEventId()));

    }
}
