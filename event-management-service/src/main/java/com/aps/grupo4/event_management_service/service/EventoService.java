package com.aps.grupo4.event_management_service.service;


import com.aps.grupo4.event_management_service.config.validations.exceptions.*;
import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import com.aps.grupo4.event_management_service.publisher.EventoPublisher;
import com.aps.grupo4.event_management_service.repository.EventoRepository;
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
    public Evento createEvento(Evento eventoNovo) {

        if (eventoNovo.getUfEvento() == null) {
            throw new SiglaUFInvalidaException("Sigla UF inválida");
        }

        Optional<Evento> eventoJaExistente = eventoRepository.findByNomeEventoIgnoreCase(eventoNovo.getNomeEvento());

        if (eventoJaExistente.isPresent()) {
            throw new EventoExistenteException("Já existe um evento com o nome " + eventoNovo.getNomeEvento());
        }

        var evento = eventoRepository.save(eventoNovo);

        try {
            eventoPublisher.publicarEvento(eventoNovo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return evento;

    }

    @Transactional
    public Evento updateEvento(Evento eventoASerAtualizado) {

        var eventoExistente = eventoRepository.findById(eventoASerAtualizado.getId())
                .orElseThrow(() -> {
                    log.info("Evento com o ID {} não encontrado. Nenhuma atualização foi feita.", eventoASerAtualizado.getId());
                    return new EventoInexistenteException(String.format("Evento com o ID %d não existe.", eventoASerAtualizado.getId()));
                });

        if (eventoASerAtualizado.getNomeEvento().equalsIgnoreCase(eventoExistente.getNomeEvento())) {
            log.error("O novo nome do evento com ID {} já existe.", eventoASerAtualizado.getId());
            throw new EventoExistenteException(String.format("O novo nome do evento com ID %d já existia. Escolha outro.", eventoASerAtualizado.getId()));
        }

        if (eventoASerAtualizado.getDataEvento().isBefore(eventoExistente.getDataEvento())) {
            log.error("A nova data do evento com ID {} é inválida", eventoASerAtualizado.getId());
            throw new DataEventoInvalidaException(String.format("A nova data do evento com ID %d é inválida. Digite uma data válida.", eventoASerAtualizado.getId()));
        }

        var registroAtualizado = eventoRepository.updateEventoById(
                eventoASerAtualizado.getId(),
                eventoASerAtualizado.getNomeEvento(),
                eventoASerAtualizado.getDataEvento(),
                eventoASerAtualizado.getLocalEvento(),
                eventoASerAtualizado.getValorIngressoEvento(),
                eventoASerAtualizado.getCapacidadeEvento(),
                eventoASerAtualizado.getDescricaoEvento(),
                eventoASerAtualizado.getUfEvento()
        );

        if (registroAtualizado == 0) {
            log.error("Ocorreu um erro ao atualizar o evento com ID {}. Nenhuma alteração foi feita!", eventoASerAtualizado.getId());
            throw new FalhaAoAtualizarEventoException(String.format("Ocorreu um erro ao atualizar o evento com ID %d. Nenhuma alteração foi feita!", eventoASerAtualizado.getId()));
        }

        return eventoRepository.findById(eventoASerAtualizado.getId())
                .orElseThrow(() -> {
                    log.error("Ocorreu um erro ao buscar o evento com o ID {}!", eventoASerAtualizado.getId());
                    return new EventoInexistenteException(String.format("Ocorreu um erro ao buscar o evento atualizado com o ID %d!", eventoASerAtualizado.getId()));
                });
    }

    @Transactional
    public Evento deleteEvento(Long idEvento) {

        var eventoDeletado = eventoRepository.findById(idEvento)
                .orElseThrow(() -> {
                    log.error("Evento com o ID {} não encontrado. Nenhuma deleção de evento ocorreu!", idEvento);
                    return new EventoInexistenteException(String.format("Deleção de evento não ocorreu pois o evento com o ID %d não existe!", idEvento));
                });

        eventoRepository.deleteById(idEvento);

        return eventoDeletado;
    }

}
