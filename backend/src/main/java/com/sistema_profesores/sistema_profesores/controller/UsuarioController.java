import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sistema_profesores.sistema_profesores.model.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        logger.info("Intentando registrar usuario con correo: {}", usuario.getCorreo());

        // Aquí iría la lógica real
        if (usuario.getCorreo().contains("admin")) {
            logger.warn("Registro bloqueado para correos de tipo administrador");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No permitido");
        }

        logger.info("Usuario {} registrado exitosamente", usuario.getCorreo());
        return ResponseEntity.ok("Usuario registrado");
    }
}
