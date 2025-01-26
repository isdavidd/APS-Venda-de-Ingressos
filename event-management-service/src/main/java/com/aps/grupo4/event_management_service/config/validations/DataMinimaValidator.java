package com.aps.grupo4.event_management_service.config.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class DataMinimaValidator implements ConstraintValidator<DataMinima, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return localDateTime != null && localDateTime.isAfter(LocalDateTime.now().plusDays(7));
    }
}
