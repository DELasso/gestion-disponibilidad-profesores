package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.Asesoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsesoriaRepository extends JpaRepository<Asesoria, Long> {
    List<Asesoria> findByEstudianteId(Long estudianteId);
}
