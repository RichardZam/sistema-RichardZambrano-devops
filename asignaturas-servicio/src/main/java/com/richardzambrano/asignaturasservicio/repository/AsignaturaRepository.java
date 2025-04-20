package com.richardzambrano.asignaturasservicio.repository;

import com.richardzambrano.asignaturasservicio.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
}