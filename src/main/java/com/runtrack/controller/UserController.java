package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
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
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String password) {
        return userService.loginUser(firstName, lastName, password)
                .map(user -> ResponseEntity.ok("Login successful"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {
        return userService.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                    user.setPassword(updatedUser.getPassword());
                    userService.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/events")
    public Set<Event> getUserEvents(@PathVariable String userId) {
        return userService.getUserEvents(userId); // 查询用户参加的事件
    }

    @PostMapping("/{userId}/events/{eventId}")
    public User addEventToUser(@PathVariable String userId, @PathVariable String eventId) {
        return userService.addEventToUser(userId, eventId);
    }

    @DeleteMapping("/{userId}/events/{eventId}")
    public User removeEventFromUser(@PathVariable String userId, @PathVariable String eventId) {
        return userService.removeEventFromUser(userId, eventId);
    }
}
