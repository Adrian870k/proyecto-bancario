package com.banco.cuentas.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CuentaDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private Long clienteId;
}
