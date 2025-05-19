package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.model.Disponibilidad;
import com.sistema_profesores.sistema_profesores.model.Usuario;
import com.sistema_profesores.sistema_profesores.repository.DisponibilidadRepository;
import com.sistema_profesores.sistema_profesores.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {

    private final DisponibilidadRepository repo;
    private final UsuarioRepository usuarioRepo;

    public DisponibilidadController(DisponibilidadRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Disponibilidad disponibilidad) {
        if (disponibilidad.getProfesor() == null) {
            return ResponseEntity.badRequest().body("Falta el profesor.");
        }
        repo.save(disponibilidad);
        return ResponseEntity.ok("Disponibilidad registrada.");
    }

    @GetMapping
    public List<Disponibilidad> listar() {
        return repo.findAll();
    }
}
