package com.richardzambrano.usuariosservicio.controller;

import com.richardzambrano.usuariosservicio.dto.AuthRequest;
import com.richardzambrano.usuariosservicio.dto.AuthResponse;
import com.richardzambrano.usuariosservicio.model.Usuario;
import com.richardzambrano.usuariosservicio.security.JwtService;
import com.richardzambrano.usuariosservicio.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // Primero, autenticar
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new AuthResponse("Credenciales inválidas: " + e.getMessage()));
        }
        // Generar token
        var token = jwtService.generateToken(userDetailsService.loadUserByUsername(request.email()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    // Endpoint para reiniciar y crear un usuario de prueba (opcional)
    @PostMapping("/reset-test")
    public ResponseEntity<Usuario> resetTestUser() {
        return ResponseEntity.ok(usuarioService.resetAndCrearUsuarioTest());
    }
}
