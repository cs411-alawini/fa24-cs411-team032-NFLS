package com.runtrack.repository;

import com.runtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
}
