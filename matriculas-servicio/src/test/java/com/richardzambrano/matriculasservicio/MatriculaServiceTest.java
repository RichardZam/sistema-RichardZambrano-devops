package com.richardzambrano.matriculasservicio;

import com.richardzambrano.matriculasservicio.client.AsignaturaClient;
import com.richardzambrano.matriculasservicio.client.UsuarioClient;
import com.richardzambrano.matriculasservicio.entity.Matricula;
import com.richardzambrano.matriculasservicio.repository.MatriculaRepository;
import com.richardzambrano.matriculasservicio.service.MatriculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MatriculaServiceTest {

    private UsuarioClient usuarioClient;
    private AsignaturaClient asignaturaClient;
    private MatriculaRepository matriculaRepository;
    private MatriculaService matriculaService;

    @BeforeEach
    void setUp() {
        usuarioClient = mock(UsuarioClient.class);
        asignaturaClient = mock(AsignaturaClient.class);
        matriculaRepository = mock(MatriculaRepository.class);
        matriculaService = new MatriculaService(usuarioClient, asignaturaClient, matriculaRepository);
    }

    @Test
    void buscarPorEstudiante_devuelveListaDeMatriculas() {
        Long idUsuario = 1L;
        Matricula m1 = new Matricula();
        m1.setId(1L);
        m1.setIdUsuario(idUsuario);
        m1.setIdAsignatura(10L);

        Matricula m2 = new Matricula();
        m2.setId(2L);
        m2.setIdUsuario(idUsuario);
        m2.setIdAsignatura(11L);

        when(matriculaRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList(m1, m2));

        List<Matricula> resultado = matriculaService.buscarPorEstudiante(idUsuario);

        assertEquals(2, resultado.size());
        verify(matriculaRepository, times(1)).findByIdUsuario(idUsuario);
    }
}
