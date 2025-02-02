package com.aps.grupo4.event_management_service.controller;


import com.aps.grupo4.event_management_service.utils.exceptions.EventoExistenteException;
import com.aps.grupo4.event_management_service.utils.exceptions.EventoInexistenteException;
import com.aps.grupo4.event_management_service.utils.exceptions.SiglaUFInvalidaException;
import com.aps.grupo4.event_management_service.entity.dtos.EventoDTO;
import com.aps.grupo4.event_management_service.service.EventoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/event-management")
@Tag(name = "Gerenciamento de Eventos", description = "Endpoints para gerenciar eventos")
public class EventManagementController {

    @Autowired
    private EventoService eventoService;

    @GetMapping(path = "/events")
    @Operation(summary = "Listar todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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
    @Operation(summary = "Buscar evento por ID", description = "Retorna um evento com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Object> buscaEventoPorId(@PathVariable("id") Long id) {
        try {
            var evento = eventoService.getEventoById(id);

            return ResponseEntity.ok().body(evento);

        } catch (EventoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/events/buscar")
    @Operation(summary = "Buscar eventos por parâmetros", description = "Retorna uma lista de eventos com base nos parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Object> buscarEventosPorParametros(
            @Parameter(description = "Data de início do evento", example = "25/12/2024 20:00")
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataInicio,

            @Parameter(description = "Data de término do evento", example = "25/12/2024 23:00")
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataFim,

            @Parameter(description = "Sigla da UF do evento", example = "SP")
            @RequestParam(required = false) String siglaUF,

            @Parameter(description = "Capacidade mínima do evento", example = "1000")
            @RequestParam(required = false) Integer capacidadeMinima,

            @Parameter(description = "Capacidade máxima do evento", example = "50000")
            @RequestParam(required = false) Integer capacidadeMaxima,

            @Parameter(description = "Local do evento", example = "Allianz Parque")
            @RequestParam(required = false) String local,

            @Parameter(description = "Nome do evento", example = "Show da Taylor Swift")
            @RequestParam(required = false) String nomeEvento)
    {
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
    @Operation(summary = "Criar um novo evento", description = "Cria um novo evento com base nos dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Object> createEvento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do evento a ser criado", required = true)
            @RequestBody EventoDTO eventoDTO)
    {
        try {
            var evento = eventoService.createEvento(eventoDTO);

            return ResponseEntity.ok().body(evento);

        } catch (SiglaUFInvalidaException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping(path = "/update-event")
    @Operation(summary = "Atualizar um evento existente", description = "Atualiza um evento existente com base nos dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Object> updateEvento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do evento a ser atualizado", required = true)
            @RequestBody EventoDTO eventoDTO)
    {
        try {
            var evento = eventoService.updateEvento(eventoDTO);

            return ResponseEntity.ok().body(evento);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        } catch (EventoInexistenteException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(path = "/delete-event/{id}")
    @Operation(summary = "Excluir um evento", description = "Exclui um evento com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Object> deleteEvento(@PathVariable("id") Long idEvento) {
        try {
            var eventoDeletado = eventoService.deleteEvento(idEvento);

            return ResponseHandler.responseBuilder(String.format("Evento com ID %d deletado com sucesso!", idEvento), HttpStatus.OK, eventoDeletado);

        } catch (EventoInexistenteException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);

        } catch (RuntimeException | JsonProcessingException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
