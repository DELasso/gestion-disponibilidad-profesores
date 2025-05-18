package com.sistema_profesores.sistema_profesores.service;

import com.sistema_profesores.sistema_profesores.dto.appointment.AppointmentRequest;
import com.sistema_profesores.sistema_profesores.dto.appointment.AppointmentResponse;
import com.sistema_profesores.sistema_profesores.model.Appointment;
import com.sistema_profesores.sistema_profesores.model.AppointmentStatus;
import com.sistema_profesores.sistema_profesores.model.User;
import com.sistema_profesores.sistema_profesores.repository.AppointmentRepository;
import com.sistema_profesores.sistema_profesores.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                            UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AppointmentResponse createAppointment(Long studentId, AppointmentRequest request) {
        User student = userRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        User professor = userRepository.findById(request.getProfessorId())
            .orElseThrow(() -> new RuntimeException("Professor not found"));

        validateAppointmentTime(request.getStartTime(), request.getEndTime());

        List<Appointment> overlappingAppointments = appointmentRepository.findOverlappingAppointments(
            student,
            request.getStartTime(),
            request.getEndTime()
        );

        if (!overlappingAppointments.isEmpty()) {
            throw new RuntimeException("Student has an overlapping appointment");
        }

        overlappingAppointments = appointmentRepository.findOverlappingAppointments(
            professor,
            request.getStartTime(),
            request.getEndTime()
        );

        if (!overlappingAppointments.isEmpty()) {
            throw new RuntimeException("Professor has an overlapping appointment");
        }

        Appointment appointment = new Appointment();
        appointment.setProfessor(professor);
        appointment.setStudent(student);
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapToResponse(savedAppointment);
    }

    @Transactional
    public AppointmentResponse cancelAppointment(Long appointmentId, Long userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getProfessor().getId().equals(userId) && 
            !appointment.getStudent().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to cancel this appointment");
        }

        if (appointment.getStartTime().minusMinutes(30).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot cancel appointment less than 30 minutes before start time");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapToResponse(savedAppointment);
    }

    public List<AppointmentResponse> getStudentAppointments(Long studentId) {
        User student = userRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        return appointmentRepository.findByStudent(student)
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public List<AppointmentResponse> getProfessorAppointments(Long professorId) {
        User professor = userRepository.findById(professorId)
            .orElseThrow(() -> new RuntimeException("Professor not found"));

        return appointmentRepository.findByProfessor(professor)
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private void validateAppointmentTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new RuntimeException("Start time must be before end time");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot create appointment in the past");
        }
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setProfessorName(appointment.getProfessor().getName());
        response.setProfessorEmail(appointment.getProfessor().getEmail());
        response.setStudentName(appointment.getStudent().getName());
        response.setStudentEmail(appointment.getStudent().getEmail());
        response.setStartTime(appointment.getStartTime());
        response.setEndTime(appointment.getEndTime());
        response.setMeetingMode(appointment.getMeetingMode());
        response.setClassroom(appointment.getClassroom());
        response.setStatus(appointment.getStatus());
        return response;
    }
} 