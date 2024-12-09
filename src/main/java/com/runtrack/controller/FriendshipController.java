package com.runtrack.controller;

import com.runtrack.entity.Friendship;
import com.runtrack.entity.User;
import com.runtrack.service.FriendshipService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }
    @GetMapping("/{userId}/friendship")
    public List<Friendship> getAllFriendships(@PathVariable String userId) {
        return friendshipService.getAllFriendships(UUID.fromString(userId));
    }

    @GetMapping("/{userId}/friends")
    public List<User> getAllFriends(@PathVariable String userId) {
        // 调用 Service 层获取好友列表
        return friendshipService.getAllFriends(UUID.fromString(userId));
    }

    @PostMapping
    public Friendship createFriendship(
            @RequestParam String userId,
            @RequestParam String friendUserId,
            @RequestParam LocalDate startDate,
            @RequestParam String friendshipLevel) {
        return friendshipService.createFriendship(UUID.fromString(userId), UUID.fromString(friendUserId), startDate, friendshipLevel);
    }
}
