package com.aps.grupo4.event_management_service.controller;


import com.aps.grupo4.event_management_service.utils.exceptions.EventoExistenteException;
import com.aps.grupo4.event_management_service.utils.exceptions.EventoInexistenteException;
import com.aps.grupo4.event_management_service.utils.exceptions.SiglaUFInvalidaException;
import com.aps.grupo4.event_management_service.entity.dtos.EventoDTO;
import com.aps.grupo4.event_management_service.service.EventoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/event-management")
public class EventManagementController {

    @Autowired
    private EventoService eventoService;

    @GetMapping(path = "/events")
    public ResponseEntity<Object> getEventos() {
        try {
            var eventos = eventoService.getEventos();

            if (eventos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            return ResponseEntity.ok().body(eventos);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @GetMapping(path = "/events/id-evento/{id}")
    public ResponseEntity<Object> buscaEventoPorId(@PathVariable("id") Long id) {
        try {
            var evento = eventoService.getEventoById(id);

            return ResponseEntity.ok().body(evento);

        } catch (EventoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/events/buscar")
    public ResponseEntity<Object> buscarEventosPorParametros(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataFim,
            @RequestParam(required = false) String siglaUF,
            @RequestParam(required = false) Integer capacidadeMinima,
            @RequestParam(required = false) Integer capacidadeMaxima,
            @RequestParam(required = false) String local,
            @RequestParam(required = false) String nomeEvento) {
        try {
            var eventos = eventoService.buscarEventosPorParametros(dataInicio, dataFim, siglaUF, capacidadeMinima, capacidadeMaxima, local, nomeEvento);

            if (eventos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            return ResponseEntity.ok().body(eventos);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/create-event")
    public ResponseEntity<Object> createEvento(@RequestBody EventoDTO eventoDTO) {
        try {
            var evento = eventoService.createEvento(eventoDTO);

            return ResponseEntity.ok().body(evento);

        } catch (SiglaUFInvalidaException | EventoExistenteException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping(path = "/update-event")
    public ResponseEntity<Object> updateEvento(@RequestBody EventoDTO eventoDTO) {
        try {
            var evento = eventoService.updateEvento(eventoDTO);

            return ResponseEntity.ok().body(evento);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (EventoInexistenteException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NO_CONTENT, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(path = "/delete-event/{id}")
    public ResponseEntity<Object> deleteEvento(@PathVariable("id") Long idEvento) {
        try {
            var eventoDeletado = eventoService.deleteEvento(idEvento);

            return ResponseHandler.responseBuilder(String.format("Evento com ID %d deletado com sucesso!", idEvento), HttpStatus.OK, eventoDeletado);

        } catch (EventoInexistenteException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NO_CONTENT, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
