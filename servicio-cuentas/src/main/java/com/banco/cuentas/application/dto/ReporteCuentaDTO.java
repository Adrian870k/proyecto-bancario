package com.banco.cuentas.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO auxiliar para representar una cuenta y sus movimientos dentro del ReporteDTO.
 */
@Getter
@Setter
public class ReporteCuentaDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private List<MovimientoDTO> movimientos;
}
