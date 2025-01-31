package com.aps.grupo4.event_management_service.publisher;

import com.aps.grupo4.event_management_service.controller.CreateEventDTO;
import com.aps.grupo4.event_management_service.entity.Evento;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventoPublisher {
    private RabbitTemplate rabbitTemplate;

    private final String FILA = "evento_criado";
    private final String routingKey = "evento.routing.key";

    public EventoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarEvento(Evento evento) throws JsonProcessingException {
        CreateEventDTO informationsEventByTicket = new CreateEventDTO(evento.getId(), evento.getCapacidadeEvento(),evento.getValorIngressoEvento());
        rabbitTemplate.convertAndSend("", FILA, informationsEventByTicket);
        System.out.println("📤 Evento publicado no RabbitMQ: " + evento.getNomeEvento());
    }
}