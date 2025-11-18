package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.MovimientoDTO;
import com.banco.cuentas.application.mapper.MovimientoMapper;
import com.banco.cuentas.domain.exception.SaldoNoDisponibleException;
import com.banco.cuentas.domain.model.Cuenta;
import com.banco.cuentas.domain.model.Movimiento;
import com.banco.cuentas.domain.repository.CuentaRepository;
import com.banco.cuentas.domain.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para manejar los movimientos.
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    private static final String SALDO_NO_DISPONIBLE = "Saldo no disponible";

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    @Transactional
    public MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO) {
        if (movimientoDTO.getValor().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El valor del movimiento no puede ser cero.");
        }

        String tipoMovimiento = (movimientoDTO.getValor().compareTo(BigDecimal.ZERO) > 0) ? "CREDITO" : "DEBITO";

        //Obtener la cuenta asociada
        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getNumeroCuenta())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada."));

        //Calcular el nuevo saldo y validar
        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal nuevoSaldo = saldoActual.add(movimientoDTO.getValor());

        //Validación de saldo no disponible
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoNoDisponibleException(SALDO_NO_DISPONIBLE);
        }

        //Actualizar el saldo de la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(movimientoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovimientoDTO> getMovimientoById(Long id) {
        return movimientoRepository.findById(id).map(movimientoMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDTO> getAllMovimientos() {
        return movimientoRepository.findAll().stream()
                .map(movimientoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<MovimientoDTO> updateMovimiento(Long id, MovimientoDTO movimientoDTO) {
        return movimientoRepository.findById(id)
                .map(existingMovimiento -> {
                    existingMovimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
                    existingMovimiento.setValor(movimientoDTO.getValor());

                    Movimiento updated = movimientoRepository.save(existingMovimiento);
                    return movimientoMapper.toDTO(updated);
                });
    }
}
