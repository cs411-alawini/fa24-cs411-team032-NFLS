package com.runtrack.repository;

import com.runtrack.entity.Friendship;
import com.runtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, String> {
    @Query("SELECT f FROM Friendship f WHERE f.userId = :userId OR f.friendUserId = :userId")
    List<Friendship> findAllFriendshipsByUserId(@Param("userId") String userId);

}
