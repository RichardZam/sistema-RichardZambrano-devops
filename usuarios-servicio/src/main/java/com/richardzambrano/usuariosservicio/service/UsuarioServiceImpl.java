package com.richardzambrano.usuariosservicio.service;

import com.richardzambrano.usuariosservicio.model.Usuario;
import com.richardzambrano.usuariosservicio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
    }


    @Override
    public Usuario resetAndCrearUsuarioTest() {
        // Elimina todos los usuarios y crea uno de prueba
        usuarioRepository.deleteAll();
        Usuario usuario = new Usuario();
        usuario.setNombre("Admin");
        usuario.setEmail("admin@escuela.com");
        usuario.setPassword(passwordEncoder.encode("123456"));
        usuario.setRol(Usuario.Rol.ADMIN);
        return usuarioRepository.save(usuario);
    }


}
