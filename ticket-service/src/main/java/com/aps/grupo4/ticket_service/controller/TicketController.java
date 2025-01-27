package com.aps.grupo4.ticket_service.controller;

import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        var ticketId = ticketService.createTicket(createTicketDTO);
        return ResponseEntity.created(URI.create("/v1/tickets/" + ticketId.toString())).build();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("ticketId") Long id){
        var user = ticketService.getTicketById(id);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping ResponseEntity<List<Ticket>> getTickets(){
        var tickets = ticketService.getTickets();
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteById(@PathVariable("ticketId") Long id){
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Void> updateById(@PathVariable("ticketId") Long id, @RequestBody UpdateTicketDTO updateTicketDTO){
        ticketService.updateById(id, updateTicketDTO);
        return ResponseEntity.noContent().build();
    }
}
