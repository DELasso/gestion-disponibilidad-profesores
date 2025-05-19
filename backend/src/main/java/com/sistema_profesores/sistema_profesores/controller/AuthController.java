package com.sistema_profesores.sistema_profesores.controller;

import com.sistema_profesores.sistema_profesores.model.Usuario;
import com.sistema_profesores.sistema_profesores.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UsuarioRepository usuarioRepo;

    public AuthController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        if (usuarioRepo.findByCorreo(usuario.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }
        usuarioRepo.save(usuario);
        return ResponseEntity.ok("Registro exitoso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credenciales) {
        Optional<Usuario> userOpt = usuarioRepo.findByCorreo(credenciales.getCorreo());
        if (userOpt.isPresent() && userOpt.get().getContraseña().equals(credenciales.getContraseña())) {
            return ResponseEntity.ok(userOpt.get()); // puedes devolver solo nombre/rol si quieres
        }
        return ResponseEntity.status(401).body("Credenciales inválidas.");
    }
}
