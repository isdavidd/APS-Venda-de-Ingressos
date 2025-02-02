package com.aps.grupo4.ticket_service.config.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PrecoMinimoValidator implements ConstraintValidator<PrecoMinimo, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal.compareTo(BigDecimal.ZERO) >= 0;
    }
}
