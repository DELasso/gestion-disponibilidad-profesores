package com.sistema_profesores.sistema_profesores.dto.availability;

import com.sistema_profesores.sistema_profesores.model.MeetingMode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ProfessorAvailabilityRequest {
    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private MeetingMode meetingMode;

    private String classroom;
} 