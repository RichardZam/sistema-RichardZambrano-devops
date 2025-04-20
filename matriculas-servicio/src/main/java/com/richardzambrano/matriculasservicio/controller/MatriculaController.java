package com.richardzambrano.matriculasservicio.controller;


import com.richardzambrano.matriculasservicio.dto.MatriculaDTO;
import com.richardzambrano.matriculasservicio.entity.Matricula;
import com.richardzambrano.matriculasservicio.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<String> registrarMatricula(@RequestBody MatriculaDTO dto) {
        String mensaje = matriculaService.matricularEstudiante(dto.getUsuarioId(), dto.getAsignaturaId());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> listarMatriculas() {
        return ResponseEntity.ok(matriculaService.listarTodas());
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Matricula>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.buscarPorEstudiante(id));
    }

    @GetMapping("/asignatura/{id}")
    public ResponseEntity<List<Matricula>> listarPorAsignatura(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.buscarPorAsignatura(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMatricula(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> editarMatricula(@PathVariable Long id, @RequestBody MatriculaDTO dto) {
        Matricula matriculaActualizada = matriculaService.actualizarMatricula(id, dto);
        return ResponseEntity.ok(matriculaActualizada);
    }
}