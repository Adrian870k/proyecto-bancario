package com.banco.cuentas.infrastructure.web;

import com.banco.cuentas.domain.exception.SaldoNoDisponibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Captura SaldoNoDisponibleException
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<Object> handleSaldoNoDisponible(SaldoNoDisponibleException ex, WebRequest request) {
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
