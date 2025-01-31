package com.aps.grupo4.ticket_service.service;

import com.aps.grupo4.ticket_service.controller.CreateTicketDTO;
import com.aps.grupo4.ticket_service.controller.UpdateTicketDTO;
import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Long createTicket(CreateTicketDTO createTicketDTO){
        var entity = new Ticket(
                createTicketDTO.eventoId(),
                null,
                null,
                createTicketDTO.preco(),
                null
        );
        var ticketSaved = ticketRepository.save(entity);
        return ticketSaved.getId();
    }

    public Optional<Ticket> getTicketById(Long ticketID){
        return ticketRepository.findById(ticketID);
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public void deleteById(Long id){
        var ticketExists = ticketRepository.existsById(id);
        if(ticketExists)
            ticketRepository.deleteById(id);
    }

    public void updateById(Long ticketId, UpdateTicketDTO updateTicketDTO){
        var ticketEntity = getTicketById(ticketId);

        if (ticketEntity.isPresent()){
            var ticket = ticketEntity.get();

            if (updateTicketDTO.preco().compareTo(BigDecimal.ZERO) != 0) {
                ticket.setPreco(updateTicketDTO.preco());
            }

            if (updateTicketDTO.status() != null)
                ticket.setStatus(updateTicketDTO.status());

            if (updateTicketDTO.nomeComprador() != null)
                ticket.setNomeComprador(updateTicketDTO.nomeComprador());


            if (updateTicketDTO.tipoIngresso() != null)
                ticket.setTipoIngresso(updateTicketDTO.tipoIngresso());

            ticketRepository.save(ticket);
        }
    }
}
