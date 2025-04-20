package com.richardzambrano.asignaturasservicio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "asignaturas")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer creditos;
}