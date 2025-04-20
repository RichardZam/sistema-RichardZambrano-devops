package com.richardzambrano.asignaturasservicio;

import com.richardzambrano.asignaturasservicio.model.Asignatura;
import com.richardzambrano.asignaturasservicio.repository.AsignaturaRepository;
import com.richardzambrano.asignaturasservicio.service.AsignaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class AsignaturasServicioApplicationTests {

	private AsignaturaRepository asignaturaRepository;
	private AsignaturaService asignaturaService;

	@BeforeEach
	void setUp() {
		asignaturaRepository = mock(AsignaturaRepository.class);
		asignaturaService = new AsignaturaService(asignaturaRepository);
	}

	@Test
	void testCrearAsignatura() {
		Asignatura asignatura = new Asignatura();
		asignatura.setId(1L);
		asignatura.setNombre("Programación");
		asignatura.setCodigo("PROG101");

		when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);

		Asignatura resultado = asignaturaService.crearAsignatura(asignatura);

		assertNotNull(resultado);
		assertEquals("Programación", resultado.getNombre());
		assertEquals("PROG101", resultado.getCodigo());
		verify(asignaturaRepository, times(1)).save(asignatura);
	}

}
