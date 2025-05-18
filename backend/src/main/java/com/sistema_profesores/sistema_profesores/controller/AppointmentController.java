package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.dto.appointment.AppointmentRequest;
import com.sistema_profesores.sistema_profesores.dto.appointment.AppointmentResponse;
import com.sistema_profesores.sistema_profesores.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<AppointmentResponse> createAppointment(
            @PathVariable Long studentId,
            @Valid @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.createAppointment(studentId, request));
    }

    @PostMapping("/{appointmentId}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(
            @PathVariable Long appointmentId,
            @RequestParam Long userId) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId, userId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AppointmentResponse>> getStudentAppointments(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(appointmentService.getStudentAppointments(studentId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AppointmentResponse>> getProfessorAppointments(
            @PathVariable Long professorId) {
        return ResponseEntity.ok(appointmentService.getProfessorAppointments(professorId));
    }
} 