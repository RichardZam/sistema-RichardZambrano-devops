package com.richardzambrano.asignaturasservicio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF para pruebas
                .authorizeHttpRequests(auth -> auth

                        // Rutas que no requieren autenticación
                        .requestMatchers("/actuator").permitAll()
                        .requestMatchers("/actuator/**").permitAll()

                        .requestMatchers("/api/asignaturas").permitAll() // Listar asignaturas público
                        .requestMatchers("/api/asignaturas/{id}").permitAll() // Obtener asignatura público
                        .requestMatchers("/api/asignaturas").hasRole("ADMIN") // Crear solo para ADMIN
                        .requestMatchers("/api/asignaturas/{id}").hasRole("ADMIN") // Editar / Eliminar solo ADMIN
                        .anyRequest().authenticated() // Resto requiere autenticación
                );

        return http.build();
    }
}