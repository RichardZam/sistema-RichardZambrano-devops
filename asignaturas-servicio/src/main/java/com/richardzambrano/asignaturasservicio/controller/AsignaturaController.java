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

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> editarAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        return asignaturaService.obtenerPorId(id)
                .map(asignaturaExistente -> {
                    // Actualizamos solo los campos necesarios
                    asignaturaExistente.setCodigo(asignatura.getCodigo());
                    asignaturaExistente.setNombre(asignatura.getNombre());
                    asignaturaExistente.setCreditos(asignatura.getCreditos());
                    Asignatura asignaturaActualizada = asignaturaService.actualizarAsignatura(asignaturaExistente);
                    return ResponseEntity.ok(asignaturaActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable Long id) {
        return asignaturaService.obtenerPorId(id)
                .map(asignatura -> {
                    asignaturaService.eliminarAsignatura(id);
                    return ResponseEntity.noContent().<Void>build(); // Explicita el tipo Void
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // También debemos devolver ResponseEntity<Void>
    }

}