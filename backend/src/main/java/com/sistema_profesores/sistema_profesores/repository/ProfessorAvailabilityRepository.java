package com.sistema_profesores.sistema_profesores.repository;

import com.sistema_profesores.sistema_profesores.model.ProfessorAvailability;
import com.sistema_profesores.sistema_profesores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.DayOfWeek;
import java.util.List;

public interface ProfessorAvailabilityRepository extends JpaRepository<ProfessorAvailability, Long> {
    List<ProfessorAvailability> findByProfessor(User professor);
    List<ProfessorAvailability> findByProfessorAndDayOfWeek(User professor, DayOfWeek dayOfWeek);
    
    @Query("SELECT pa FROM ProfessorAvailability pa WHERE pa.professor = ?1 AND pa.dayOfWeek = ?2 AND " +
           "((pa.startTime <= ?3 AND pa.endTime > ?3) OR (pa.startTime < ?4 AND pa.endTime >= ?4) OR " +
           "(pa.startTime >= ?3 AND pa.endTime <= ?4))")
    List<ProfessorAvailability> findOverlappingAvailability(User professor, DayOfWeek dayOfWeek, 
                                                          LocalTime startTime, LocalTime endTime);
} 