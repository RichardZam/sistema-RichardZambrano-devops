package com.richardzambrano.matriculasservicio;

import com.richardzambrano.matriculasservicio.entity.Matricula;
import com.richardzambrano.matriculasservicio.repository.MatriculaRepository;
import com.richardzambrano.matriculasservicio.service.MatriculaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
				"org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration",
		"spring.jpa.hibernate.ddl-auto=none",
		"spring.sql.init.mode=never"
})
@TestPropertySource(properties = {
		"spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false"
})
class MatriculasServicioApplicationTests {

	@MockBean
	private MatriculaRepository matriculaRepository;

	@Autowired
	private MatriculaService matriculaService;

	@Test
	void contextLoads() {
		assertNotNull(matriculaService);
	}

	@Test
	void testBuscarPorEstudiante() {
		Long idEstudiante = 1L;

		Matricula matricula1 = new Matricula();
		matricula1.setId(1L);
		matricula1.setIdUsuario(idEstudiante);
		matricula1.setIdAsignatura(3L);
		matricula1.setFechaRegistro(LocalDate.now());

		Matricula matricula2 = new Matricula();
		matricula2.setId(2L);
		matricula2.setIdUsuario(idEstudiante);
		matricula2.setIdAsignatura(4L);
		matricula2.setFechaRegistro(LocalDate.now());

		when(matriculaRepository.findByIdUsuario(idEstudiante))
				.thenReturn(List.of(matricula1, matricula2));

		List<Matricula> resultado = matriculaService.buscarPorEstudiante(idEstudiante);

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals(idEstudiante, resultado.get(0).getIdUsuario());
		assertEquals(idEstudiante, resultado.get(1).getIdUsuario());

		verify(matriculaRepository, times(1)).findByIdUsuario(idEstudiante);
	}
}