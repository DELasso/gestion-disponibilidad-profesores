package com.sistema_profesores.sistema_profesores.dto.availability;

import com.sistema_profesores.sistema_profesores.model.MeetingMode;
import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ProfessorAvailabilityResponse {
    private Long id;
    private String professorName;
    private String professorEmail;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private MeetingMode meetingMode;
    private String classroom;
} 