package com.richardzambrano.matriculasservicio.client;


import com.richardzambrano.matriculasservicio.dto.MatriculaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "asignaturas-servicio")
public interface AsignaturaClient {
    @GetMapping("/api/asignaturas/{id}")
    MatriculaResponseDTO.AsignaturaDTO getAsignaturaById(@PathVariable Long id);
}