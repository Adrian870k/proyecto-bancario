package com.banco.clientes.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Persona {
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 10)
    private String genero;

    private int edad;

    @Column(unique = true, nullable = false, length = 20)
    private String identificacion;

    @Column(length = 200)
    private String direccion;

    @Column(length = 15)
    private String telefono;
}
