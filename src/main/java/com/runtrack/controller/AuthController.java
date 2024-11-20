package com.runtrack.controller;

import com.runtrack.entity.User;
import com.runtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    // 登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String userId = credentials.get("userId");
        String password = credentials.get("password");

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            System.out.println("Login successful for user: " + userId);
            return ResponseEntity.ok().body(Map.of("message", "Login successful", "user", user.get()));
        } else {
            System.out.println("Invalid credentials for user: " + userId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // 注册
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        if (userRepository.existsById(newUser.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        userRepository.save(newUser);
        return ResponseEntity.ok().body("User registered successfully");
    }
}
