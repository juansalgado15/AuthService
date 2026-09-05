package com.authservice.authservice.repository;

import com.authservice.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository responsible for managing users in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username.
     *
     * @param username username to search for
     * @return the user if found
     */
    Optional<User> findByUsername(String username);
}
