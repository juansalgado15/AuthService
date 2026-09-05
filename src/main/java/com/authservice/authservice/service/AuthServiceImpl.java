package com.authservice.authservice.service;

import com.authservice.authservice.dto.LoginRequest;
import com.authservice.authservice.dto.RegisterRequest;
import com.authservice.authservice.entity.User;
import com.authservice.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the authentication service.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    /**
     * Constructor used to inject the user repository.
     *
     * @param userRepository repository used to access users
     */
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user in the database.
     */
    @Override
    public String register(RegisterRequest request) {

        // Check whether the username is already registered
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username is already registered";
        }

        // Create a new user with the received credentials
        User user = new User(
                request.getUsername(),
                request.getPassword()
        );

        // Save the user in the database
        userRepository.save(user);

        return "User registered successfully";
    }

    /**
     * Authenticates a user using the provided credentials.
     */
    @Override
    public String login(LoginRequest request) {

        // Search for the user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        // Verify that the user exists and the password is correct
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return "Authentication successful";
        }

        // Return an error when the credentials are incorrect
        return "Authentication failed";
    }
}