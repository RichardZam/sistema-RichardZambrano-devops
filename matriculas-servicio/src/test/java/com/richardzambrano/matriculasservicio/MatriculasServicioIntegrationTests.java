package com.richardzambrano.matriculasservicio;

import com.richardzambrano.matriculasservicio.client.AsignaturaClient;
import com.richardzambrano.matriculasservicio.client.UsuarioClient;
import com.richardzambrano.matriculasservicio.dto.MatriculaDTO;
import com.richardzambrano.matriculasservicio.dto.MatriculaResponseDTO;
import com.richardzambrano.matriculasservicio.entity.Matricula;
import com.richardzambrano.matriculasservicio.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test") // Usa el perfil de pruebas

class MatriculasServicioIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UsuarioClient usuarioClient;

    @MockBean
    private AsignaturaClient asignaturaClient;

    @MockBean
    private MatriculaRepository matriculaRepository;

    @Test
    void testRegistrarMatricula() {
        // Datos de prueba para usuario y asignatura
        Long usuarioId = 1L;
        Long asignaturaId = 2L;

        // Mock de los datos externos de UsuarioClient
        MatriculaResponseDTO.UsuarioDTO usuarioMock = new MatriculaResponseDTO.UsuarioDTO();
        usuarioMock.setId(usuarioId);
        usuarioMock.setNombre("Juan Perez");
        usuarioMock.setEmail("juan.perez@gmail.com");
        usuarioMock.setRol("ESTUDIANTE");

        when(usuarioClient.getUsuarioById(usuarioId)).thenReturn(usuarioMock);

        // Mock de los datos externos de AsignaturaClient
        MatriculaResponseDTO.AsignaturaDTO asignaturaMock = new MatriculaResponseDTO.AsignaturaDTO();
        asignaturaMock.setId(asignaturaId);
        asignaturaMock.setCodigo("MAT101");
        asignaturaMock.setNombre("Matemáticas");
        asignaturaMock.setCreditos(3);

        when(asignaturaClient.getAsignaturaById(asignaturaId)).thenReturn(asignaturaMock);

        // Mock del comportamiento del repositorio
        Matricula nuevaMatricula = new Matricula();
        nuevaMatricula.setId(1L);
        nuevaMatricula.setIdUsuario(usuarioId);
        nuevaMatricula.setIdAsignatura(asignaturaId);
        nuevaMatricula.setFechaRegistro(LocalDate.now());

        when(matriculaRepository.save(any(Matricula.class))).thenReturn(nuevaMatricula);

        // DTO Simulado para el llamado al endpoint
        MatriculaDTO matriculaDTO = new MatriculaDTO();
        matriculaDTO.setUsuarioId(usuarioId);
        matriculaDTO.setAsignaturaId(asignaturaId);

        // Llama al endpoint POST /api/matriculas
        webTestClient.post()
                .uri("/api/matriculas")
                .bodyValue(matriculaDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                    assertTrue(response.contains("Estudiante Juan Perez matriculado en Matemáticas"));
                });

        // Verificamos que se llamaron los mocks de los clientes y el repositorio
        verify(usuarioClient, times(1)).getUsuarioById(usuarioId);
        verify(asignaturaClient, times(1)).getAsignaturaById(asignaturaId);
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }
}