package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.CuentaDTO;
import com.banco.cuentas.application.mapper.CuentaMapper;
import com.banco.cuentas.domain.model.Cuenta;
import com.banco.cuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las cuentas.
 */
@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    @Transactional
    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {
        if (cuentaRepository.existsById(cuentaDTO.getNumeroCuenta())) {
            throw new IllegalArgumentException("Ya existe una cuenta con el número: " + cuentaDTO.getNumeroCuenta());
        }
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(cuentaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CuentaDTO> getCuentaByNumero(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .map(cuentaMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaDTO> getAllCuentas() {
        return cuentaRepository.findAll().stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<CuentaDTO> updateCuenta(String numeroCuenta, CuentaDTO cuentaDTO) {
        return cuentaRepository.findById(numeroCuenta)
                .map(existingCuenta -> {
                    // Actualiza solo los campos permitidos
                    existingCuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
                    existingCuenta.setEstado(cuentaDTO.getEstado());
                    // El saldo inicial y clienteId no deberían modificarse aquí

                    Cuenta updatedCuenta = cuentaRepository.save(existingCuenta);
                    return cuentaMapper.toDTO(updatedCuenta);
                });
    }
}
