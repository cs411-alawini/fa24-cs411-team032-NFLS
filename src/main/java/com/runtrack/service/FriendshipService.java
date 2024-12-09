package com.runtrack.service;

import com.runtrack.entity.Friendship;
import com.runtrack.entity.User;
import com.runtrack.repository.FriendshipRepository;
import com.runtrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    // 获取所有好友关系
    public List<Friendship> getAllFriendships(UUID userId) {
        return friendshipRepository.findAllFriendshipsByUserId(userId);
    }

    // 获取所有好友的详细信息
    public List<User> getAllFriends(UUID userId) {
        // 获取所有与当前用户相关的好友关系
        List<Friendship> friendships = friendshipRepository.findAllFriendshipsByUserId(userId);

        // 提取所有相关的 User IDs
        Set<UUID> friendIds = friendships.stream()
                .flatMap(f -> Stream.of(f.getUserId(), f.getFriendUserId()))
                .filter(id -> !id.equals(userId)) // 排除当前用户的 ID
                .collect(Collectors.toSet());

        // 查询所有好友的详细信息
        return (List<User>) userRepository.findAllById(friendIds);
    }

    // 创建好友关系
    public Friendship createFriendship(UUID userId, UUID friendUserId, LocalDate startDate, String friendshipLevel) {
        // 检查用户和好友是否存在
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!userRepository.existsById(friendUserId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Friend user not found");
        }

        Friendship friendship = new Friendship();
        friendship.setFriendshipId(UUID.randomUUID());
        friendship.setUserId(userId);
        friendship.setFriendUserId(friendUserId);
        friendship.setStartDate(startDate);
        friendship.setFriendshipLevel(friendshipLevel);

        return friendshipRepository.save(friendship);
    }
}
