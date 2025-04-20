package com.richardzambrano.matriculasservicio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MatriculaResponseDTO {
    private Long id;
    private LocalDate fechaRegistro;
    private UsuarioDTO usuario;
    private AsignaturaDTO asignatura;

    @Data
    public static class UsuarioDTO{
        private Long id;
        private String nombre;
        private String email;
        private String rol;
    }

    @Data
    public static class AsignaturaDTO {
        private Long id;
        private String codigo;
        private String nombre;
        private Integer creditos;
    }
}