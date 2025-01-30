package com.aps.grupo4.event_management_service.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UFConverter implements AttributeConverter<UFEnum, String> {

    @Override
    public String convertToDatabaseColumn(UFEnum uf) {
        return uf != null ? uf.name() : UFEnum.XX.name();
    }

    @Override
    public UFEnum convertToEntityAttribute(String siglaUf) {
        return siglaUf != null ? UFEnum.valueOf(siglaUf) : UFEnum.XX;
    }
}
