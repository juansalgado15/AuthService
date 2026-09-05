package com.authservice.authservice.controller;

import com.authservice.authservice.dto.LoginRequest;
import com.authservice.authservice.dto.RegisterRequest;
import com.authservice.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for user registration and authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor used to inject the authentication service.
     *
     * @param authService service that handles authentication logic
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     *
     * @param request registration data
     * @return registration result
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        String result = authService.register(request);

        if (result.equals("Username is already registered")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(result);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    /**
     * Authenticates an existing user.
     *
     * @param request login credentials
     * @return authentication result
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request) {

        String result = authService.login(request);

        if (result.equals("Authentication failed")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(result);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}