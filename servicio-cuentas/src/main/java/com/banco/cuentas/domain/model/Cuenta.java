package com.banco.cuentas.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @Column(name = "numero_cuenta", unique = true, nullable = false, length = 20)
    private String numeroCuenta; // "número de cuenta"

    @Column(name = "tipo_cuenta", nullable = false, length = 10)
    private String tipoCuenta; // "tipo de cuenta"

    @Column(name = "saldo_inicial", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoInicial; // "saldo inicial"

    @Column(nullable = false)
    private Boolean estado; // "estado"

    /**
     * ID del cliente al que pertenece la cuenta.
     * Necesario para la funcionalidad de reportes.
     */
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}
