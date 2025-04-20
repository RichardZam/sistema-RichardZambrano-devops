package com.richardzambrano.matriculasservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.richardzambrano.matriculasservicio.client")
public class MatriculasServicioApplication {
	public static void main(String[] args) {
		SpringApplication.run(MatriculasServicioApplication.class, args);
	}
}
