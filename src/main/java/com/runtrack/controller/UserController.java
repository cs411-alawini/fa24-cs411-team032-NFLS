package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email,
                                            @RequestParam String password) {
        Optional<User> user = userService.loginUser(email, password);
        return user.map(u -> ResponseEntity.ok("Login successful"))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId,
                                           @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable String userId) {
        List<Event> events = userService.getUserEvents(userId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/{userId}/events/{eventId}")
    public ResponseEntity<String> addEventToUser(@PathVariable String userId,
                                                 @PathVariable String eventId) {
        try {
            userService.addEventToUser(userId, eventId);
            return ResponseEntity.ok("Event added to user successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/events/{eventId}")
    public ResponseEntity<String> removeEventFromUser(@PathVariable String userId,
                                                      @PathVariable String eventId) {
        try {
            userService.removeEventFromUser(userId, eventId);
            return ResponseEntity.ok("Event removed from user successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
