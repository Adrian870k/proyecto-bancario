package com.banco.clientes.application.mapper;

import com.banco.clientes.application.dto.ClienteDTO;
import com.banco.clientes.application.dto.CreateClienteDTO;
import com.banco.clientes.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(CreateClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente cliente = new Cliente();

        // Mapeo de Persona
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        
        // Mapeo de Cliente 
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());

        return cliente;
    }

    /**
     * Convierte una entidad Cliente al DTO de respuesta.
     */
    public ClienteDTO toDTO(Cliente entity) {
        if (entity == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        
        dto.setClienteId(entity.getClienteId());
        dto.setNombre(entity.getNombre());
        dto.setGenero(entity.getGenero());
        dto.setEdad(entity.getEdad());
        dto.setIdentificacion(entity.getIdentificacion());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setEstado(entity.getEstado());
        
        return dto;
    }
}
