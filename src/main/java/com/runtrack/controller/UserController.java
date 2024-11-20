package com.runtrack.controller;

import com.runtrack.entity.Event;
import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        System.out.println("UserController instantiated");
        this.userService = userService;
    }

    @GetMapping("/paged")
    public Page<User> getAllUsersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy) {
        return userService.getAllUsersPaged(page, size, sortBy);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
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
