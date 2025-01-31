package com.aps.grupo4.event_management_service.repository;

import com.aps.grupo4.event_management_service.entity.Evento;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepositoryCustom {
    List<Evento> buscarEventosPorParametros(
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            String siglaUF,
            Integer capacidadeMinima,
            Integer capacidadeMaxima,
            String local,
            String nomeEvento
    );
}
