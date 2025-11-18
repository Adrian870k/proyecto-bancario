package com.banco.clientes.application.service;
import com.banco.clientes.application.dto.ClienteDTO;
import com.banco.clientes.application.dto.CreateClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    /**
     * Crea un nuevo cliente.
     */
    ClienteDTO createCliente(CreateClienteDTO createClienteDTO);

    /**
     * Obtiene un cliente por su ID.
     */
    Optional<ClienteDTO> getClienteById(Long clienteId);

    /**
     * Obtiene todos los clientes.
     */
    List<ClienteDTO> getAllClientes();

    /**
     * Actualiza un cliente existente.
     */
    Optional<ClienteDTO> updateCliente(Long clienteId, CreateClienteDTO createClienteDTO);

    /**
     * Elimina un cliente por su ID.
     */
    boolean deleteCliente(Long clienteId);
}
