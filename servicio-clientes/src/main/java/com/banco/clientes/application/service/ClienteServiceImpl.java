package com.banco.clientes.application.service;

import com.banco.clientes.application.dto.ClienteDTO;
import com.banco.clientes.application.dto.CreateClienteDTO;
import com.banco.clientes.application.mapper.ClienteMapper;
import com.banco.clientes.domain.model.Cliente;
import com.banco.clientes.domain.repository.ClienteRepository;
import com.banco.clientes.infrastructure.messaging.ClienteEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para manejar los clientes .
 */
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteEventPublisher clienteEventPublisher;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              ClienteMapper clienteMapper,
                              ClienteEventPublisher clienteEventPublisher) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.clienteEventPublisher = clienteEventPublisher;
    }

    @Override
    @Transactional
    public ClienteDTO createCliente(CreateClienteDTO createClienteDTO) {
        if (clienteRepository.findByIdentificacion(createClienteDTO.getIdentificacion()).isPresent()) {
            throw new IllegalArgumentException("La identificación ya está registrada.");
        }

        Cliente cliente = clienteMapper.toEntity(createClienteDTO);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        clienteEventPublisher.publicarEventoCliente(
                "CLIENTE_CREADO: " + clienteGuardado.getClienteId()
        );

        return clienteMapper.toDTO(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(clienteMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> updateCliente(Long clienteId, CreateClienteDTO dto) {
        return clienteRepository.findById(clienteId)
                .map(existingCliente -> {
                    existingCliente.setNombre(dto.getNombre());
                    existingCliente.setGenero(dto.getGenero());
                    existingCliente.setEdad(dto.getEdad());
                    existingCliente.setIdentificacion(dto.getIdentificacion());
                    existingCliente.setDireccion(dto.getDireccion());
                    existingCliente.setTelefono(dto.getTelefono());
                    existingCliente.setContrasena(dto.getContrasena());
                    existingCliente.setEstado(dto.getEstado());

                    Cliente updatedCliente = clienteRepository.save(existingCliente);

                    // Publicar evento de actualización
                    clienteEventPublisher.publicarEventoCliente(
                            "CLIENTE_ACTUALIZADO: " + updatedCliente.getClienteId()
                    );

                    return clienteMapper.toDTO(updatedCliente);
                });
    }

    @Override
    @Transactional
    public boolean deleteCliente(Long clienteId) {
        if (clienteRepository.existsById(clienteId)) {
            clienteRepository.deleteById(clienteId);

            // Publicar evento de eliminación
            clienteEventPublisher.publicarEventoCliente(
                    "CLIENTE_ELIMINADO: " + clienteId
            );

            return true;
        }
        return false;
    }
}

