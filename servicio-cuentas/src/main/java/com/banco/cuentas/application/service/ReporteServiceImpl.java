package com.banco.cuentas.application.service;

import com.banco.cuentas.application.dto.MovimientoDTO;
import com.banco.cuentas.application.dto.ReporteCuentaDTO;
import com.banco.cuentas.application.dto.ReporteDTO;
import com.banco.cuentas.application.mapper.MovimientoMapper;
import com.banco.cuentas.domain.model.Cuenta;
import com.banco.cuentas.domain.model.Movimiento;
import com.banco.cuentas.domain.repository.CuentaRepository;
import com.banco.cuentas.domain.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar el reporte.
 */
@Service
public class ReporteServiceImpl implements ReporteService{
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    public ReporteServiceImpl(CuentaRepository cuentaRepository,
                              MovimientoRepository movimientoRepository,
                              MovimientoMapper movimientoMapper) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        //Encontrar las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        if (cuentas.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado o no tiene cuentas.");
        }

        //Preparar el DTO de reporte
        ReporteDTO reporte = new ReporteDTO();
        reporte.setClienteId(clienteId);
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);

        //Iterar por cada cuenta para obtener sus movimientos
        List<ReporteCuentaDTO> reportesCuenta = cuentas.stream().map(cuenta -> {

            //Obtener movimientos en el rango de fechas
            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaRange(
                    cuenta.getNumeroCuenta(),
                    fechaInicio.atStartOfDay(),
                    fechaFin.atTime(LocalTime.MAX)
            );

            //Mapear movimientos a DTO
            List<MovimientoDTO> movimientosDTO = movimientos.stream()
                    .map(movimientoMapper::toDTO)
                    .collect(Collectors.toList());

            //Crear DTO de la cuenta
            ReporteCuentaDTO reporteCuentaDTO = new ReporteCuentaDTO();
            reporteCuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            reporteCuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
            reporteCuentaDTO.setSaldoActual(cuenta.getSaldoInicial());
            reporteCuentaDTO.setMovimientos(movimientosDTO);

            return reporteCuentaDTO;

        }).collect(Collectors.toList());

        reporte.setCuentas(reportesCuenta);
        return reporte;
    }
}
