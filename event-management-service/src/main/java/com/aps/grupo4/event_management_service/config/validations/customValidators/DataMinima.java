package com.aps.grupo4.event_management_service.config.validations.customValidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataMinimaValidator.class)
public @interface DataMinima {
    String message() default "A data do evento deve ser ao menos uma semana a partir da data atual";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
