package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.dto.availability.ProfessorAvailabilityRequest;
import com.sistema_profesores.sistema_profesores.dto.availability.ProfessorAvailabilityResponse;
import com.sistema_profesores.sistema_profesores.service.ProfessorAvailabilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class ProfessorAvailabilityController {

    private final ProfessorAvailabilityService availabilityService;

    public ProfessorAvailabilityController(ProfessorAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/professor/{professorId}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<ProfessorAvailabilityResponse> createAvailability(
            @PathVariable Long professorId,
            @Valid @RequestBody ProfessorAvailabilityRequest request) {
        return ResponseEntity.ok(availabilityService.createAvailability(professorId, request));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<ProfessorAvailabilityResponse>> getProfessorAvailabilities(
            @PathVariable Long professorId) {
        return ResponseEntity.ok(availabilityService.getProfessorAvailabilities(professorId));
    }
} 