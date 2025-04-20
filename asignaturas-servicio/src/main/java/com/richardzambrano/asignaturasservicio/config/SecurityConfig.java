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
                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF para pruebas (en producción hay que tener cuidado)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/asignaturas/**").permitAll() // 🟢 Permitimos todo en /api/asignaturas/
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated() // 🔒 El resto requiere auth
                );

        return http.build();
    }
}
