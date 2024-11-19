package com.runtrack.controller;

import com.runtrack.entity.User;
import com.runtrack.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
