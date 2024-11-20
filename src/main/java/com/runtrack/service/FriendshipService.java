package com.runtrack.service;

import com.runtrack.entity.Friendship;
import com.runtrack.entity.User;
import com.runtrack.repository.FriendshipRepository;
import com.runtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public List<Friendship> getAllFriendships(String userId) {
        return friendshipRepository.findAllFriendshipsByUserId(userId);
    }

    public List<User> getAllFriends(String userId) {
        // 获取所有与当前用户相关的好友关系
        List<Friendship> friendships = friendshipRepository.findAllFriendshipsByUserId(userId);

        // 提取所有相关的 User IDs
        Set<String> userIds = friendships.stream()
                .flatMap(f -> Stream.of(f.getUserId(), f.getFriendUserId()))
                .filter(id -> !id.equals(userId)) // 排除当前用户的 ID
                .collect(Collectors.toSet());

        // 查询所有好友的详细信息
        return userRepository.findAllById(userIds);
    }



    public Friendship createFriendship(String userId, String friendUserId, LocalDate startDate, String friendshipLevel) {
        Friendship friendship = new Friendship();
        friendship.setFriendshipId(UUID.randomUUID().toString());
        friendship.setUserId(userId);
        friendship.setFriendUserId(friendUserId);
        friendship.setStartDate(startDate);
        friendship.setFriendshipLevel(friendshipLevel);

        return friendshipRepository.save(friendship);
    }
}
