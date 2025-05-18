package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.User;
import com.sistema_profesores.sistema_profesores.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
    boolean existsByEmail(String email);
} 