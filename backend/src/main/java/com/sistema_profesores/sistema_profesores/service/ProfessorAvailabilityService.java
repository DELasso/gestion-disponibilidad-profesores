package com.sistema_profesores.sistema_profesores.service;

import com.sistema_profesores.sistema_profesores.dto.availability.ProfessorAvailabilityRequest;
import com.sistema_profesores.sistema_profesores.dto.availability.ProfessorAvailabilityResponse;
import com.sistema_profesores.sistema_profesores.model.ProfessorAvailability;
import com.sistema_profesores.sistema_profesores.model.User;
import com.sistema_profesores.sistema_profesores.repository.ProfessorAvailabilityRepository;
import com.sistema_profesores.sistema_profesores.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorAvailabilityService {

    private final ProfessorAvailabilityRepository availabilityRepository;
    private final UserRepository userRepository;

    public ProfessorAvailabilityService(ProfessorAvailabilityRepository availabilityRepository,
                                      UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ProfessorAvailabilityResponse createAvailability(Long professorId, 
                                                         ProfessorAvailabilityRequest request) {
        User professor = userRepository.findById(professorId)
            .orElseThrow(() -> new RuntimeException("Professor not found"));

        validateTimeRange(request.getStartTime(), request.getEndTime());

        List<ProfessorAvailability> overlappingAvailabilities = 
            availabilityRepository.findOverlappingAvailability(
                professor,
                request.getDayOfWeek(),
                request.getStartTime(),
                request.getEndTime()
            );

        if (!overlappingAvailabilities.isEmpty()) {
            throw new RuntimeException("Time slot overlaps with existing availability");
        }

        ProfessorAvailability availability = new ProfessorAvailability();
        availability.setProfessor(professor);
        availability.setDayOfWeek(request.getDayOfWeek());
        availability.setStartTime(request.getStartTime());
        availability.setEndTime(request.getEndTime());
        availability.setMeetingMode(request.getMeetingMode());
        availability.setClassroom(request.getClassroom());

        ProfessorAvailability savedAvailability = availabilityRepository.save(availability);
        return mapToResponse(savedAvailability);
    }

    public List<ProfessorAvailabilityResponse> getProfessorAvailabilities(Long professorId) {
        User professor = userRepository.findById(professorId)
            .orElseThrow(() -> new RuntimeException("Professor not found"));

        return availabilityRepository.findByProfessor(professor)
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private void validateTimeRange(LocalTime startTime, LocalTime endTime) {
        LocalTime minTime = LocalTime.of(8, 0);
        LocalTime maxTime = LocalTime.of(16, 0);

        if (startTime.isBefore(minTime) || endTime.isAfter(maxTime)) {
            throw new RuntimeException("Time must be between 8:00 AM and 4:00 PM");
        }

        if (!startTime.isBefore(endTime)) {
            throw new RuntimeException("Start time must be before end time");
        }
    }

    private ProfessorAvailabilityResponse mapToResponse(ProfessorAvailability availability) {
        ProfessorAvailabilityResponse response = new ProfessorAvailabilityResponse();
        response.setId(availability.getId());
        response.setProfessorName(availability.getProfessor().getName());
        response.setProfessorEmail(availability.getProfessor().getEmail());
        response.setDayOfWeek(availability.getDayOfWeek());
        response.setStartTime(availability.getStartTime());
        response.setEndTime(availability.getEndTime());
        response.setMeetingMode(availability.getMeetingMode());
        response.setClassroom(availability.getClassroom());
        return response;
    }
} 