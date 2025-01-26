package com.aps.grupo4.event_management_service.validations;

import com.aps.grupo4.event_management_service.entity.Evento;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventoValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void capacidadeEventoDeveSerMaiorQueZero() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(LocalDateTime.now().plusDays(8))
                .localEvento("Estádio Municipal")
                .valorIngressoEvento(BigDecimal.valueOf(200))
                .capacidadeEvento(0)
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertFalse(violations.isEmpty());
    }

    @Test
    void valorIngressoNaoPodeSerNulo() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(LocalDateTime.now().plusDays(8))
                .localEvento("Estádio Municipal")
                .capacidadeEvento(1000)
                .valorIngressoEvento(null)
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertFalse(violations.isEmpty());
    }

    @Test
    void valorIngressoNaoPodeSerZero() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(LocalDateTime.now().plusDays(8))
                .localEvento("Estádio Municipal")
                .capacidadeEvento(1000)
                .valorIngressoEvento(BigDecimal.ZERO)
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertFalse(violations.isEmpty());
    }

    @Test
    void dataEventoNaoPodeSerNula() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(null)
                .localEvento("Estádio Municipal")
                .capacidadeEvento(1000)
                .valorIngressoEvento(BigDecimal.valueOf(200))
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertFalse(violations.isEmpty());
    }

    @Test
    void dataEventoDeveSerPeloMenosSeteDiasNoFuturo() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(LocalDateTime.now().plusDays(6))
                .localEvento("Estádio Municipal")
                .capacidadeEvento(1000)
                .valorIngressoEvento(BigDecimal.valueOf(200))
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertFalse(violations.isEmpty());
    }

    @Test
    void eventoValido() {
        var evento = Evento.builder()
                .nomeEvento("Show de Rock")
                .dataEvento(LocalDateTime.now().plusDays(8))
                .localEvento("Estádio Municipal")
                .capacidadeEvento(1000)
                .valorIngressoEvento(BigDecimal.valueOf(200))
                .descricaoEvento("Show incrível com várias bandas")
                .build();

        Set<ConstraintViolation<Evento>> violations = validator.validate(evento);

        assertTrue(violations.isEmpty());
    }
}
