package com.sistema_profesores.sistema_profesores.dto.auth;

import com.sistema_profesores.sistema_profesores.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private UserRole role;
    private String name;
} 