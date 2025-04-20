package com.richardzambrano.asignaturasservicio.controller;

import com.richardzambrano.asignaturasservicio.model.Asignatura;
import com.richardzambrano.asignaturasservicio.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@RequestBody Asignatura asignatura) {
        return ResponseEntity.ok(asignaturaService.crearAsignatura(asignatura));
    }

    @GetMapping
    public ResponseEntity<List<Asignatura>> listarAsignaturas() {
        return ResponseEntity.ok(asignaturaService.listarTodas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorId(@PathVariable Long id) {
        return asignaturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}