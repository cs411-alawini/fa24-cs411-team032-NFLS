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
    public List<Friendship> getAllFriendships(String userId) {
        return friendshipRepository.findAllFriendshipsByUserId(userId);
    }

    // 获取所有好友的详细信息
    public List<User> getAllFriends(String userId) {
        List<Friendship> friendships = friendshipRepository.findAllFriendshipsByUserId(userId);

        Set<String> friendIds = friendships.stream()
                .flatMap(f -> Stream.of(f.getUserId(), f.getFriendUserId()))
                .filter(id -> !id.equals(userId))
                .collect(Collectors.toSet());

        return (List<User>) userRepository.findAllById(friendIds);
    }

    // 创建好友关系
    public Friendship createFriendship(String userId, String friendUserId, LocalDate startDate, String friendshipLevel) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!userRepository.existsById(friendUserId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Friend user not found");
        }

        Friendship friendship = new Friendship();
        friendship.setFriendshipId(UUID.randomUUID().toString());
        friendship.setUserId(userId);
        friendship.setFriendUserId(friendUserId);
        friendship.setStartDate(startDate);
        friendship.setFriendshipLevel(friendshipLevel);

        return friendshipRepository.save(friendship);
    }
}
