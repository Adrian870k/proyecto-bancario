package com.banco.cuentas.infrastructure.web;

import com.banco.cuentas.application.dto.MovimientoDTO;
import com.banco.cuentas.application.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO nuevoMovimiento = movimientoService.createMovimiento(movimientoDTO);
        return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.getAllMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable Long id) {
        return movimientoService.getMovimientoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.updateMovimiento(id, movimientoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
