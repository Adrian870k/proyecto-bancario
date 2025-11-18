package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.CuentaDTO;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    CuentaDTO createCuenta(CuentaDTO cuentaDTO);

    Optional<CuentaDTO> getCuentaByNumero(String numeroCuenta);

    List<CuentaDTO> getAllCuentas();

    Optional<CuentaDTO> updateCuenta(String numeroCuenta, CuentaDTO cuentaDTO);
}
