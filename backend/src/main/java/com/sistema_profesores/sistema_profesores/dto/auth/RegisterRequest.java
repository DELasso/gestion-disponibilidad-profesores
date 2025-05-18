package com.sistema_profesores.sistema_profesores.dto.auth;

import com.sistema_profesores.sistema_profesores.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@udem\\.edu\\.co$", 
             message = "Email must be a valid UDEM institutional email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
             message = "Password must be at least 8 characters long and contain at least one digit, " +
                      "one lowercase letter, one uppercase letter, and one special character")
    private String password;

    private UserRole role;
} 