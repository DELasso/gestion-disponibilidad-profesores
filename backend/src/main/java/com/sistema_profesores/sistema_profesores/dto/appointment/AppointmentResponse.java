package com.sistema_profesores.sistema_profesores.dto.appointment;

import com.sistema_profesores.sistema_profesores.model.AppointmentStatus;
import com.sistema_profesores.sistema_profesores.model.MeetingMode;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private String professorName;
    private String professorEmail;
    private String studentName;
    private String studentEmail;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MeetingMode meetingMode;
    private String classroom;
    private AppointmentStatus status;
} 