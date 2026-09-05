package com.authservice.authservice.service;

import com.authservice.authservice.dto.LoginRequest;
import com.authservice.authservice.dto.RegisterRequest;

/**
 * Defines the operations related to user authentication.
 */
public interface AuthService {

    /**
     * Registers a new user.
     *
     * @param request registration data
     * @return message indicating the registration result
     */
    String register(RegisterRequest request);

    /**
     * Authenticates an existing user.
     *
     * @param request login credentials
     * @return message indicating the authentication result
     */
    String login(LoginRequest request);
}