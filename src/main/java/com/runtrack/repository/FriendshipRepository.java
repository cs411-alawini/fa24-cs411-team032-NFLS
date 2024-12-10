package com.runtrack.repository;

import com.runtrack.entity.Friendship;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, String> {

    // 根据用户 ID 查找所有好友关系
    @Query("SELECT * FROM MakeFriends WHERE UserId = :userId OR FriendUserId = :userId")
    List<Friendship> findAllFriendshipsByUserId(String userId);
}
