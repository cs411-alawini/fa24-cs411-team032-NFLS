package com.runtrack.service;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.repository.EventRepository;
import com.runtrack.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public UserService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    // 用户注册
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // 用户登录
    public Optional<User> loginUser(String firstName, String lastName, String password) {
        Optional<User> user = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    // 根据 ID 查找用户
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    // 保存用户信息
    public User save(User user) {
        return userRepository.save(user);
    }

    // 更新用户信息
    public User updateUser(String id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    // 获取用户参加的事件
    public Set<Event> getUserEvents(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getHostedEvents();
    }

    // 给用户添加事件
    public User addEventToUser(String userId, String eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        user.addHostedEvent(event); // 维护双向关联
        return userRepository.save(user);
    }

    // 移除用户的事件
    public User removeEventFromUser(String userId, String eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        user.removeHostedEvent(event); // 维护双向关联
        return userRepository.save(user);
    }
}

