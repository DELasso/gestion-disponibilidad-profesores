package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.Appointment;
import com.sistema_profesores.sistema_profesores.model.AppointmentStatus;
import com.sistema_profesores.sistema_profesores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByProfessor(User professor);
    List<Appointment> findByStudent(User student);
    List<Appointment> findByProfessorAndStatus(User professor, AppointmentStatus status);
    List<Appointment> findByStudentAndStatus(User student, AppointmentStatus status);
    
    @Query("SELECT a FROM Appointment a WHERE (a.professor = ?1 OR a.student = ?1) AND " +
           "((a.startTime <= ?2 AND a.endTime > ?2) OR (a.startTime < ?3 AND a.endTime >= ?3) OR " +
           "(a.startTime >= ?2 AND a.endTime <= ?3)) AND a.status = 'SCHEDULED'")
    List<Appointment> findOverlappingAppointments(User user, LocalDateTime startTime, LocalDateTime endTime);
} 