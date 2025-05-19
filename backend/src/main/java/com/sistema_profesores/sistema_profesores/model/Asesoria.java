package com.sistema_profesores.sistema_profesores.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String hora;

    @ManyToOne
    private Usuario estudiante;

    @ManyToOne
    private Disponibilidad disponibilidad;
}
