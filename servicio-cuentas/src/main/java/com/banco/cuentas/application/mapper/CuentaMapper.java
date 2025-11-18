package com.banco.cuentas.application.mapper;

import com.banco.cuentas.application.dto.CuentaDTO;
import com.banco.cuentas.domain.model.Cuenta;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entidades Cuenta a DTO y viceversa.
 */
@Component
public class CuentaMapper {
    public CuentaDTO toDTO(Cuenta entity) {
        if (entity == null) {
            return null;
        }
        CuentaDTO dto = new CuentaDTO();
        dto.setNumeroCuenta(entity.getNumeroCuenta());
        dto.setTipoCuenta(entity.getTipoCuenta());
        dto.setSaldoInicial(entity.getSaldoInicial());
        dto.setEstado(entity.getEstado());
        dto.setClienteId(entity.getClienteId());
        return dto;
    }

    public Cuenta toEntity(CuentaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cuenta entity = new Cuenta();
        entity.setNumeroCuenta(dto.getNumeroCuenta());
        entity.setTipoCuenta(dto.getTipoCuenta());
        entity.setSaldoInicial(dto.getSaldoInicial());
        entity.setEstado(dto.getEstado());
        entity.setClienteId(dto.getClienteId());
        return entity;
    }
}
