package com.runtrack.repository;

import com.runtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByUserIdAndPassword(String userId, String password);
}
