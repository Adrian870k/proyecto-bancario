package com.banco.cuentas.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO principal para devolver el reporte
 */
@Getter
@Setter
public class ReporteDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long clienteId;
    private List<ReporteCuentaDTO> cuentas;
}
