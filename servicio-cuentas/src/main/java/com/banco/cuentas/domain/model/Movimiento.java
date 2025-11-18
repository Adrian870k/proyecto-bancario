package com.banco.cuentas.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha; // "Fecha"

    @Column(name = "tipo_movimiento", nullable = false, length = 10)
    private String tipoMovimiento; // "tipo movimiento" (DEBITO/CREDITO)

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor; // "valor" (positivo o negativo) [cite: 5, 9]

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo; // "saldo" (saldo *después* del movimiento)

    /**
     * Relación con la cuenta. Un movimiento pertenece a una cuenta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_numero", referencedColumnName = "numero_cuenta", nullable = false)
    private Cuenta cuenta;
}
