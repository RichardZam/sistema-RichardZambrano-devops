package com.richardzambrano.matriculasservicio.client;

import com.richardzambrano.matriculasservicio.dto.MatriculaResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-servicio")
public interface UsuarioClient {
    @GetMapping("/api/usuarios/{id}")
    MatriculaResponseDTO.UsuarioDTO getUsuarioById(@PathVariable Long id);
}