package com.richardzambrano.asignaturasservicio.service;

import com.richardzambrano.asignaturasservicio.model.Asignatura;
import com.richardzambrano.asignaturasservicio.repository.AsignaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;

    public Asignatura crearAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }
    public Optional<Asignatura> obtenerPorId(Long id) {
        return asignaturaRepository.findById(id);
    }

}