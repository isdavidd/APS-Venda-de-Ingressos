package com.aps.grupo4.ticket_service.config.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrecoMinimoValidator.class)
public @interface PrecoMinimo {
    String message() default "O preço não pode ser negativo.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
