package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email,
                                            @RequestParam String password) {
        Optional<User> user = userService.loginUser(email, password);
        return user.map(u -> ResponseEntity.ok("Login successful"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }

    // 根据 ID 查找用户
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 更新用户信息
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId,
                                           @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // 获取用户参加的所有事件
    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable UUID userId) {
        List<Event> events = userService.getUserEvents(userId);
        return ResponseEntity.ok(events);
    }

    // 给用户添加事件
    @PostMapping("/{userId}/events/{eventId}")
    public ResponseEntity<String> addEventToUser(@PathVariable UUID userId,
                                                 @PathVariable UUID eventId) {
        try {
            userService.addEventToUser(userId, eventId);
            return ResponseEntity.ok("Event added to user successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 移除用户的事件
    @DeleteMapping("/{userId}/events/{eventId}")
    public ResponseEntity<String> removeEventFromUser(@PathVariable UUID userId,
                                                      @PathVariable UUID eventId) {
        try {
            userService.removeEventFromUser(userId, eventId);
            return ResponseEntity.ok("Event removed from user successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
