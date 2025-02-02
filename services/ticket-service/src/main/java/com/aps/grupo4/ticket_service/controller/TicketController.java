package com.aps.grupo4.ticket_service.controller;

import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "Ingressos", description = "Endpoints para operações com ingressos")
@RequestMapping("/tickets-service")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Criar ingresso", description = "Cria um ingresso no banco de dados.")
    @PostMapping("/ticket")
    public ResponseEntity<Object> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        try {
            var ticketId = ticketService.createTicket(createTicketDTO);
            return ResponseEntity.ok().body(ticketId);
        }
        catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Operation(summary = "Buscar ingresso por ID", description = "Retorna um ingresso com base no ID informado.")
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Object> getTicketById(@PathVariable("ticketId") Long id){
        try {
            Optional<Ticket> ticket = ticketService.getTicketById(id);
            if (ticket.isPresent())
                return ResponseEntity.ok().body(ticket);
            else
                return ResponseHandler.responseBuilder(String.format("Ingresso com ID %d não encontrado.", id), HttpStatus.NOT_FOUND, ticket);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Operation(summary = "Listar todos os ingressos", description = "Retorna uma lista de todos os ingressos cadastrados.")
    @GetMapping("/get-tickets")
    public ResponseEntity<Object> getTickets(){
        try {
            var tickets = ticketService.getTickets();
            if (tickets.isEmpty())
                return ResponseHandler.responseBuilder(("Não foram encontrados ingressos."), HttpStatus.NOT_FOUND, tickets);
            return ResponseEntity.ok().body(tickets);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Operation(summary = "Deletar ingresso por ID", description = "Deleta um ingresso com base no ID informado.")
    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<Object> deleteById(@PathVariable("ticketId") Long id){
        try {
            Optional<Ticket> deletedTicket = ticketService.deleteById(id);
            if (deletedTicket.isPresent())
                return ResponseHandler.responseBuilder(String.format("Ingresso com ID %d deletado com sucesso.", id), HttpStatus.OK, deletedTicket);
            else
                return ResponseHandler.responseBuilder(String.format("Ingresso com ID %d não encontrado.", id), HttpStatus.NOT_FOUND, deletedTicket);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Operation(summary = "Atualizar ingresso por ID", description = "Atualiza um ingresso com base no ID informado e nos dados fornecidos.")
    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<Object> updateById(@PathVariable("ticketId") Long id, @RequestBody UpdateTicketDTO updateTicketDTO){
        try {
            Optional<Ticket> ticket = ticketService.updateById(id, updateTicketDTO);
            if (ticket.isPresent())
                return ResponseEntity.ok().body(ticket);
            else
                return ResponseHandler.responseBuilder(String.format("Ingresso com ID %d não encontrado.", id), HttpStatus.NOT_FOUND, ticket);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/ticket/user/{userId}")
    @Operation(summary = "Buscar ingressos por ID de usuário", description = "Retorna todos os ingressos do usuário com base no ID informado.")
    public ResponseEntity<Object> getTicketsByUserId(@PathVariable("userId") Long userId){
        try {
            List<Ticket> payment = ticketService.getTicketsByUserId(userId);
            if (!payment.isEmpty())
                return ResponseEntity.ok().body(payment);
            else
                return ResponseHandler.responseBuilder(String.format("Não foram ecncontrados ingressos comprados pelo usuário com id: %d.", userId), HttpStatus.NOT_FOUND, payment);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
