package com.sistema_profesores.sistema_profesores.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dia;
    private String hora;
    private String modalidad; // presencial o virtual
    private String aula;

    @ManyToOne
    private Usuario profesor;
}
