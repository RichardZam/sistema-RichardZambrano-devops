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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration",
                "spring.jpa.hibernate.ddl-auto=none",
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureWebTestClient
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false"
})
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
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testRegistrarMatricula() {
        Long usuarioId = 1L;
        Long asignaturaId = 2L;

        MatriculaResponseDTO.UsuarioDTO usuarioMock = new MatriculaResponseDTO.UsuarioDTO();
        usuarioMock.setId(usuarioId);
        usuarioMock.setNombre("Juan Perez");
        usuarioMock.setEmail("juan.perez@gmail.com");
        usuarioMock.setRol("ESTUDIANTE");

        when(usuarioClient.getUsuarioById(usuarioId)).thenReturn(usuarioMock);

        MatriculaResponseDTO.AsignaturaDTO asignaturaMock = new MatriculaResponseDTO.AsignaturaDTO();
        asignaturaMock.setId(asignaturaId);
        asignaturaMock.setCodigo("MAT101");
        asignaturaMock.setNombre("Matemáticas");
        asignaturaMock.setCreditos(3);

        when(asignaturaClient.getAsignaturaById(asignaturaId)).thenReturn(asignaturaMock);

        Matricula nuevaMatricula = new Matricula();
        nuevaMatricula.setId(1L);
        nuevaMatricula.setIdUsuario(usuarioId);
        nuevaMatricula.setIdAsignatura(asignaturaId);
        nuevaMatricula.setFechaRegistro(LocalDate.now());

        when(matriculaRepository.save(any(Matricula.class))).thenReturn(nuevaMatricula);

        MatriculaDTO matriculaDTO = new MatriculaDTO();
        matriculaDTO.setUsuarioId(usuarioId);
        matriculaDTO.setAsignaturaId(asignaturaId);

        webTestClient.post()
                .uri("/api/matriculas")
                .bodyValue(matriculaDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                    assertTrue(response.contains("Estudiante Juan Perez matriculado en Matemáticas"));
                });

        verify(usuarioClient, times(1)).getUsuarioById(usuarioId);
        verify(asignaturaClient, times(1)).getAsignaturaById(asignaturaId);
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }
}