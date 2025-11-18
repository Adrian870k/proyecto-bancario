package com.banco.clientes.application.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClienteDTO {
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;
}
