package com.banco.cuentas.infrastructure.web;

import com.banco.cuentas.application.dto.CuentaDTO;
import com.banco.cuentas.application.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> createCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO nuevaCuenta = cuentaService.createCuenta(cuentaDTO);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
        return ResponseEntity.ok(cuentaService.getAllCuentas());
    }


    @GetMapping("/{numero}")
    public ResponseEntity<CuentaDTO> getCuentaByNumero(@PathVariable("numero") String numeroCuenta) {
        return cuentaService.getCuentaByNumero(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numero}")
    public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable("numero") String numeroCuenta, @RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.updateCuenta(numeroCuenta, cuentaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
