package com.aps.grupo4.event_management_service.controller;


import com.aps.grupo4.event_management_service.config.validations.EventoExistenteExcepetion;
import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.service.EventoService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping(path = "/event/id-evento/{id}")
    public ResponseEntity<Object> buscaEventoPorId(@PathVariable("id") Integer id) {
        try {
            var evento = eventoService.getEventoById(id);

            if (evento == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            return ResponseEntity.ok().body(evento);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/event/nome-evento/{nomeEvento}")
    public ResponseEntity<Object> buscaEventoPorNome(@PathVariable("nomeEvento") String nomeEvento) {
        try {
            var evento = eventoService.getEventoByNome(nomeEvento);

            if (evento == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            return ResponseEntity.ok().body(evento);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/create-event")
    public ResponseEntity<Object> createEvento(@RequestBody Evento eventoNovo) {
        try {
            var evento = eventoService.createEvento(eventoNovo);

            return ResponseEntity.ok().body(evento);

        } catch (EventoExistenteExcepetion e) {
            return  ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (RuntimeException e) {
            return  ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
