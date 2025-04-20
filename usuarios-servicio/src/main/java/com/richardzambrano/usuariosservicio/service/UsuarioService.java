package com.richardzambrano.usuariosservicio.service;

import com.richardzambrano.usuariosservicio.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> listarTodos();
    Usuario obtenerPorId(Long id);
    Usuario obtenerPorEmail(String email);
    // Método opcional para resetear y crear un usuario de prueba
    Usuario resetAndCrearUsuarioTest();
}
