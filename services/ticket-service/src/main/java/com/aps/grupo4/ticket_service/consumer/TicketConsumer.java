package com.aps.grupo4.ticket_service.consumer;

import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.repository.TicketRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketConsumer {

    private final TicketRepository ticketRepository;

    public TicketConsumer(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @RabbitListener(queues = "evento_criado")
    public void processarEvento(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreatedEventDTO eventoDTO = objectMapper.readValue(message, CreatedEventDTO.class);
            List<Ticket> ingressos = new ArrayList<>();
            for (int i = 0; i < eventoDTO.getCapacidadeEvento(); i++) {
                ingressos.add(new Ticket(eventoDTO.getId(), null, null, eventoDTO.getValorIngressoEvento(), null));
            }
            ticketRepository.saveAll(ingressos);
            System.out.println("✅ " + eventoDTO.getCapacidadeEvento() + " ingressos criados!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
