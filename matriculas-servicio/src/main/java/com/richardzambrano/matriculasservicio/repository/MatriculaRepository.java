package com.richardzambrano.matriculasservicio.repository;

import com.richardzambrano.matriculasservicio.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByIdUsuario(Long idUsuario);

    List<Matricula> findByIdAsignatura(Long idAsignatura);

}
