package com.richardzambrano.usuariosservicio;

import com.richardzambrano.usuariosservicio.model.Usuario;
import com.richardzambrano.usuariosservicio.repository.UsuarioRepository;
import com.richardzambrano.usuariosservicio.service.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        usuarioService = new UsuarioServiceImpl(usuarioRepository, passwordEncoder);
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("test");
        usuario.setEmail("test@email.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioService.obtenerPorId(1L);

        // Assert echo por Richard Zambrano
        assertNotNull(resultado);
        assertEquals("test", resultado.getNombre());
        assertEquals("test@email.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorIdNotFound() {
        // Arrange
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.obtenerPorId(999L);
        });

        assertEquals("Usuario no encontrado con id: 999", exception.getMessage());
    }
    @Test
    void testActualizarUsuario() {
        // Arrange
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNombre("Nombre Actual");
        usuarioExistente.setEmail("actual@email.com");
        usuarioExistente.setRol(Usuario.Rol.ESTUDIANTE);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre("Nombre Nuevo");
        usuarioActualizado.setEmail("nuevo@email.com");
        usuarioActualizado.setPassword("nuevoPassword");
        usuarioActualizado.setRol(Usuario.Rol.DOCENTE);

        when(usuarioRepository.save(any())).thenReturn(usuarioActualizado);

        // Act
        Usuario resultado = usuarioService.actualizarUsuario(1L, usuarioActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nombre Nuevo", resultado.getNombre());
        assertEquals("nuevo@email.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).save(any());
    }
    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        usuarioService.eliminarUsuario(1L);

        // Assert
        verify(usuarioRepository, times(1)).delete(usuario);
    }

}
