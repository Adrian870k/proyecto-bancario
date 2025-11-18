package com.banco.cuentas.domain.repository;

import com.banco.cuentas.domain.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.numeroCuenta = :numeroCuenta AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByCuentaAndFechaRange(
            @Param("numeroCuenta") String numeroCuenta,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    /**
     * Obtiene el último movimiento de una cuenta para calcular el saldo.
     */
    Movimiento findTopByCuentaNumeroCuentaOrderByFechaDesc(String numeroCuenta);
}
