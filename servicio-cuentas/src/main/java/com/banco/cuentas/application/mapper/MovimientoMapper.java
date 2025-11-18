package com.banco.cuentas.application.mapper;

import com.banco.cuentas.application.dto.MovimientoDTO;
import com.banco.cuentas.domain.model.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {
    public MovimientoDTO toDTO(Movimiento entity) {
        if (entity == null) {
            return null;
        }
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setTipoMovimiento(entity.getTipoMovimiento());
        dto.setValor(entity.getValor());
        dto.setSaldo(entity.getSaldo());
        if (entity.getCuenta() != null) {
            dto.setNumeroCuenta(entity.getCuenta().getNumeroCuenta());
        }
        return dto;
    }

    public Movimiento toEntity(MovimientoDTO dto) {
        if (dto == null) {
            return null;
        }
        Movimiento entity = new Movimiento();
        entity.setId(dto.getId());
        entity.setFecha(dto.getFecha());
        entity.setTipoMovimiento(dto.getTipoMovimiento());
        entity.setValor(dto.getValor());
        entity.setSaldo(dto.getSaldo());
        return entity;
    }
}
