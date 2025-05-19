package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.model.Asesoria;
import com.sistema_profesores.sistema_profesores.repository.AsesoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesorias")
public class AsesoriaController {

    private final AsesoriaRepository repo;

    public AsesoriaController(AsesoriaRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Asesoria asesoria) {
        repo.save(asesoria);
        return ResponseEntity.ok("Asesoría agendada.");
    }

    @GetMapping("/{estudianteId}")
    public List<Asesoria> listar(@PathVariable Long estudianteId) {
        return repo.findByEstudianteId(estudianteId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Asesoría cancelada.");
        }
        return ResponseEntity.notFound().build();
    }
}
