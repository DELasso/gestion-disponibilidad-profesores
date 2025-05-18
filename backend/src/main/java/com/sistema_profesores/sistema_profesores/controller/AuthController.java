package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.dto.auth.AuthResponse;
import com.sistema_profesores.sistema_profesores.dto.auth.LoginRequest;
import com.sistema_profesores.sistema_profesores.dto.auth.RegisterRequest;
import com.sistema_profesores.sistema_profesores.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
} 