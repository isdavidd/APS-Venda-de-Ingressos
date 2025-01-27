package com.aps.grupo4.ticket_service.service;

import com.aps.grupo4.ticket_service.controller.CreateTicketDTO;
import com.aps.grupo4.ticket_service.controller.UpdateTicketDTO;
import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.repository.TicketRepository;
import org.springframework.stereotype.Service;

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
                null,
                null,
                null,
                createTicketDTO.price(),
                "Disponível",
                Instant.now(),
                null
        );
        var userSaved = ticketRepository.save(entity);
        return userSaved.getTicketID();
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

            if (updateTicketDTO.price() != 0.0)
                ticket.setPrice(updateTicketDTO.price());

            if (updateTicketDTO.status() != null)
                ticket.setStatus(updateTicketDTO.status());

            if (updateTicketDTO.buyerName() != null)
                ticket.setBuyerName(updateTicketDTO.buyerName());

            if (updateTicketDTO.paymentMethod() != null)
                ticket.setPaymentMethod(updateTicketDTO.paymentMethod());

            if (updateTicketDTO.ticketType() != null)
                ticket.setTicketType(updateTicketDTO.ticketType());

            ticketRepository.save(ticket);
        }
    }
}
