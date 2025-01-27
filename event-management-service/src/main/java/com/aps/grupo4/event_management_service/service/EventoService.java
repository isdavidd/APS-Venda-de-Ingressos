package com.aps.grupo4.event_management_service.service;


import com.aps.grupo4.event_management_service.config.validations.EventoExistenteExcepetion;
import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.repository.EventoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getEventos() {
        try {
            var eventos = eventoRepository.findAll();

            if (eventos.isEmpty()) {
                log.info("Não há eventos!");
                return List.of();
            }

            return eventos;

        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro inesperado!");
        }
    }

    public Evento getEventoById(Integer id) {
        try {
            Optional<Evento> evento = eventoRepository.findById(id);

            if (evento.isEmpty()) {
                log.info("Evento com o ID {} não encontrado!", id);
                return null;
            }

            return evento.get();

        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro inesperado!");
        }
    }

    public Evento getEventoByNome(String nome) {
        try {

            var evento = eventoRepository.findByNomeEvento(nome);

            if (evento.isEmpty()) {
                log.info("Evento com o nome {} não encontrado!", nome);
                return null;
            }

            return evento.get();

        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro inesperado!");
        }
    }

    public Evento createEvento(Evento eventoNovo) {


        Optional<Evento> eventoJaExistente = eventoRepository.findByNomeEvento(eventoNovo.getNomeEvento());

        if (eventoJaExistente.isPresent()) {
            throw new EventoExistenteExcepetion("Já existe um evento com o nome " + eventoNovo.getNomeEvento());
        }

        var evento = eventoRepository.save(eventoNovo);

        return evento;


    }
}
