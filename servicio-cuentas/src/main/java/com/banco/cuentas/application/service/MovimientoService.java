package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.MovimientoDTO;

import java.util.List;
import java.util.Optional;

public interface MovimientoService {
    MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO);

    Optional<MovimientoDTO> getMovimientoById(Long id);

    List<MovimientoDTO> getAllMovimientos();

    Optional<MovimientoDTO> updateMovimiento(Long id, MovimientoDTO movimientoDTO);
}
