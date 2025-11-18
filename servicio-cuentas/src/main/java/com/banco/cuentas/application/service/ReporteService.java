package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.ReporteDTO;
import java.time.LocalDate;

public interface ReporteService {
    /**
     * Genera el reporte de estado de cuenta para un cliente y rango de fechas.
     */
    ReporteDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
