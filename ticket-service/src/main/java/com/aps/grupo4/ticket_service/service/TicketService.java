package com.aps.grupo4.ticket_service.service;

import com.aps.grupo4.ticket_service.controller.CreateTicketDTO;
import com.aps.grupo4.ticket_service.controller.UpdateTicketDTO;
import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Ticket ticketSaved = ticketRepository.save(entity);
        return ticketSaved.getId();
    }

    public Optional<Ticket> getTicketById(Long ticketID){
        return ticketRepository.findById(ticketID);
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> deleteById(Long id){
        Optional<Ticket> ticketExist = ticketRepository.findById(id);
        if(ticketExist.isPresent()) {
            ticketRepository.deleteById(id);
            return ticketExist;
        }
        return ticketExist;
    }

    public Optional<Ticket> updateById(Long ticketId, UpdateTicketDTO updateTicketDTO){
        var ticketEntity = getTicketById(ticketId);

        if (ticketEntity.isPresent()){
            var ticket = ticketEntity.get();
            if (updateTicketDTO.preco() != null)
                ticket.setPreco(updateTicketDTO.preco());

            if (updateTicketDTO.status() != null)
                ticket.setStatus(updateTicketDTO.status());

            if (updateTicketDTO.nomeComprador() != null)
                ticket.setNomeComprador(updateTicketDTO.nomeComprador());


            if (updateTicketDTO.tipoIngresso() != null)
                ticket.setTipoIngresso(updateTicketDTO.tipoIngresso());

            return Optional.of(ticketRepository.save(ticket));
        }
        return Optional.empty();
    }
}
