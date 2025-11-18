package com.banco.cuentas.infrastructure.web;

import com.banco.cuentas.application.dto.ReporteDTO;
import com.banco.cuentas.application.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/reportes")
    public ResponseEntity<ReporteDTO> getReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("cliente") Long clienteId) {

        ReporteDTO reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}
