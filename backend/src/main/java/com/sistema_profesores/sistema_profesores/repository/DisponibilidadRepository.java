package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {
    List<Disponibilidad> findByProfesorId(Long profesorId);
}
