package com.aps.grupo4.event_management_service.service;


import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import com.aps.grupo4.event_management_service.entity.dtos.EventoDTO;
import com.aps.grupo4.event_management_service.publisher.EventoPublisher;
import com.aps.grupo4.event_management_service.repository.EventoRepository;
import com.aps.grupo4.event_management_service.utils.exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoPublisher eventoPublisher;

    public List<Evento> getEventos() {

        var eventos = eventoRepository.findAll();

        if (eventos.isEmpty()) {
            log.info("Não há eventos");
            return List.of();
        }

        return eventos;

    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Evento com o ID {} não encontrado", id);
                    return new EventoInexistenteException(String.format("Evento com o ID %d não existe", id));
                });
    }

    public List<Evento> buscarEventosPorParametros(
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            String siglaUF,
            Integer capacidadeMinima,
            Integer capacidadeMaxima,
            String local,
            String nomeEvento
    ) {
        var eventos = eventoRepository.buscarEventosPorParametros(
                dataInicio,
                dataFim,
                siglaUF,
                capacidadeMinima,
                capacidadeMaxima,
                local,
                nomeEvento
        );

        if (eventos.isEmpty()) {
            log.info("Não há eventos");
            return List.of();
        }

        return eventos;
    }

    @Transactional
    public Evento createEvento(EventoDTO eventoDTO) {

        if (eventoDTO.getEstadoOrUFEvento() == null) {
            throw new SiglaUFInvalidaException("Sigla UF/Nome estado é inválida(o)");
        }

        UFEnum ufEvento = null;

        if (eventoDTO.getEstadoOrUFEvento().length() == 2) {
            ufEvento = UFEnum.valueOf(eventoDTO.getEstadoOrUFEvento().toUpperCase());

        } else {
            ufEvento = UFEnum.getUFFromEstado(eventoDTO.getEstadoOrUFEvento());
        }

        var evento = Evento.builder()
                .nomeEvento(eventoDTO.getNomeEvento())
                .dataEvento(eventoDTO.getDataEvento())
                .localEvento(eventoDTO.getLocalEvento())
                .valorIngressoEvento(eventoDTO.getValorIngressoEvento())
                .ufEvento(ufEvento)
                .descricaoEvento(eventoDTO.getDescricaoEvento())
                .capacidadeEvento(eventoDTO.getCapacidadeEvento())
                .build();

        eventoRepository.save(evento);

        try {
            eventoPublisher.publicarEvento(evento);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }

        return evento;

    }

    @Transactional
    public Evento updateEvento(EventoDTO eventoDTO) {

        if (eventoDTO.getIdEvento() == null) {
            throw new IllegalArgumentException("ID do evento deve ser informado");
        }

        var eventoExistente = eventoRepository.findById(eventoDTO.getIdEvento())
                .orElseThrow(() -> {
                    log.info("Evento com o ID {} não encontrado. Nenhuma atualização foi feita.", eventoDTO.getIdEvento());
                    return new EventoInexistenteException(String.format("Evento com o ID %d não existe.", eventoDTO.getIdEvento()));
                });
        
        if (eventoDTO.getDataEvento().isBefore(eventoExistente.getDataEvento())) {
            log.error("A nova data do evento com ID {} é inválida", eventoDTO.getIdEvento());
            throw new DataEventoInvalidaException(String.format("A nova data do evento com ID %d é inválida. Digite uma data válida.", eventoDTO.getIdEvento()));
        }

        var registroAtualizado = eventoRepository.updateEventoById(
                eventoDTO.getIdEvento(),
                eventoDTO.getNomeEvento(),
                eventoDTO.getDataEvento(),
                eventoDTO.getLocalEvento(),
                eventoDTO.getValorIngressoEvento(),
                eventoDTO.getCapacidadeEvento(),
                eventoDTO.getDescricaoEvento(),
                UFEnum.getUFFromEstado(eventoDTO.getEstadoOrUFEvento())
        );

        if (registroAtualizado == 0) {
            log.error("Ocorreu um erro ao atualizar o evento com ID {}. Nenhuma alteração foi feita", eventoDTO.getIdEvento());
            throw new FalhaAoAtualizarEventoException(String.format("Ocorreu um erro ao atualizar o evento com ID %d. Nenhuma alteração foi feita", eventoDTO.getIdEvento()));
        }

        return eventoRepository.findById(eventoDTO.getIdEvento())
                .orElseThrow(() -> {
                    log.error("Ocorreu um erro ao buscar o evento com o ID {}", eventoDTO.getIdEvento());
                    return new EventoInexistenteException(String.format("Ocorreu um erro ao buscar o evento atualizado com o ID %d", eventoDTO.getIdEvento()));
                });
    }

    @Transactional
    public Evento deleteEvento(Long idEvento) {

        var eventoDeletado = eventoRepository.findById(idEvento)
                .orElseThrow(() -> {
                    log.error("Evento com o ID {} não encontrado. Nenhuma deleção de evento ocorreu", idEvento);
                    return new EventoInexistenteException(String.format("Deleção de evento não ocorreu pois o evento com o ID %d não existe", idEvento));
                });

        eventoRepository.deleteById(idEvento);

        return eventoDeletado;
    }

}
