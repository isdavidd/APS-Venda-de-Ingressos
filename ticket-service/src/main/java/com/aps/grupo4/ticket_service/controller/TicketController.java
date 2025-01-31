package com.aps.grupo4.ticket_service.controller;

import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        try {
            var ticketId = ticketService.createTicket(createTicketDTO);
            return ResponseEntity.ok().body(ticketId);
        }
        catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{ticketId}")
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

    @GetMapping ResponseEntity<Object> getTickets(){
        try {
            var tickets = ticketService.getTickets();
            if (tickets.isEmpty())
                return ResponseHandler.responseBuilder(("Não foram encontrados ingressos."), HttpStatus.NOT_FOUND, tickets);
            return ResponseEntity.ok().body(tickets);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{ticketId}")
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

    @PutMapping("/{ticketId}")
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
}
