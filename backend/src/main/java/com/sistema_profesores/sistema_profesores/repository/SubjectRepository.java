package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.Subject;
import com.sistema_profesores.sistema_profesores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByProfessor(User professor);
    List<Subject> findByNameContainingIgnoreCase(String name);
    List<Subject> findByCodeContainingIgnoreCase(String code);
} 