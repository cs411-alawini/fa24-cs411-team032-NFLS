package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(
                Map.of("message", "Register successful",
                        "userId", registeredUser.getUserId().toString(),
                        "email", registeredUser.getEmail())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(
            @RequestParam String email,
            @RequestParam String password) {
        Optional<User> user = userService.loginUser(email, password);
        return user.map(u -> ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "email", u.getEmail()
        ))).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "message", "Invalid credentials"
        )));
    }




    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable UUID userId) {
        List<Event> events = userService.getUserEvents(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
