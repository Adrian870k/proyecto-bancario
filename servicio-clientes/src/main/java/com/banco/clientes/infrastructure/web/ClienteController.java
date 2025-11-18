package com.banco.clientes.infrastructure.web;

import com.banco.clientes.application.dto.ClienteDTO;
import com.banco.clientes.application.dto.CreateClienteDTO;
import com.banco.clientes.application.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los clientes.
 */
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody CreateClienteDTO createClienteDTO) {
        ClienteDTO nuevoCliente = clienteService.createCliente(createClienteDTO);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable("id") Long clienteId) {
        return clienteService.getClienteById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable("id") Long clienteId, @RequestBody CreateClienteDTO createClienteDTO) {
        return clienteService.updateCliente(clienteId, createClienteDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long clienteId) {
        if (clienteService.deleteCliente(clienteId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
