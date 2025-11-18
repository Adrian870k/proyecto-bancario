package com.banco.clientes.servicio_cuentas;

import com.banco.cuentas.domain.model.Cuenta;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServicioCuentasApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * Prueba unitaria para la entidad de dominio Cuenta.
     * Verifica que los getters y setters de la entidad funcionen.
     */
    @Test
    void testCuentaEntityGettersSetters() {
        Cuenta cuenta = new Cuenta();

        cuenta.setNumeroCuenta("4909-1234");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(new BigDecimal("1000.50"));
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        assertEquals("4909-1234", cuenta.getNumeroCuenta());
        assertEquals("Ahorros", cuenta.getTipoCuenta());
        assertEquals(new BigDecimal("1000.50"), cuenta.getSaldoInicial());
        assertEquals(true, cuenta.getEstado());
        assertEquals(1L, cuenta.getClienteId());
    }

}
