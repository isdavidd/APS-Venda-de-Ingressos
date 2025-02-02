package com.aps.grupo4.event_management_service.publisher;

import com.aps.grupo4.event_management_service.entity.dtos.QueuedNewEventDTO;
import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.entity.dtos.QueuedCancelledEventDTO;
import com.aps.grupo4.event_management_service.entity.dtos.QueuedUpdatedEventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventoPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "evento_exchange";

    //TODO Colocar essas kays no application.yml
    private static final String ROUTING_KEY_EVENTO_CRIADO = "evento.criado";
    private static final String ROUTING_KEY_CAPACIDADE_AUMENTADA = "evento.capacidade.aumentada";
    private static final String ROUTING_KEY_CAPACIDADE_REDUZIDA = "evento.capacidade.reduzida";
    private static final String ROUTING_KEY_EVENTO_DELETADO = "evento.cancelado";

    public EventoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarEventoCriado(Evento evento) throws JsonProcessingException {

        QueuedNewEventDTO informationsEventByTicket = new QueuedNewEventDTO(
                evento.getId(),
                evento.getNomeEvento(),
                evento.getCapacidadeEvento(),
                evento.getValorIngressoEvento()
        );

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_EVENTO_CRIADO, informationsEventByTicket);
        System.out.println("📤 Evento publicado no RabbitMQ: " + evento.getNomeEvento());
    }

    public void publicarEventoAtualizado(Evento evento, boolean isCapacidadeAumentada, Integer diferencaCapacidade) throws JsonProcessingException {

        QueuedUpdatedEventDTO informationsEventByTicket = new QueuedUpdatedEventDTO(
                evento.getId(),
                evento.getNomeEvento(),
                evento.getCapacidadeEvento(),
                evento.getValorIngressoEvento(),
                diferencaCapacidade
        );

        if (isCapacidadeAumentada) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_CAPACIDADE_AUMENTADA, informationsEventByTicket);

        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_CAPACIDADE_REDUZIDA, informationsEventByTicket);
        }

        System.out.println("📤 Atualização de evento publicada no RabbitMQ: " + evento.getNomeEvento());
    }

    public void publicarCancelamentoDeEvento(Evento evento) throws JsonProcessingException {

        QueuedCancelledEventDTO deletedEvent = new QueuedCancelledEventDTO(
                evento.getId(),
                evento.getNomeEvento(),
                evento.getCapacidadeEvento()
        );

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_EVENTO_DELETADO, deletedEvent);
        System.out.println("📤 Deleção de Evento publicada no RabbitMQ: " + evento.getNomeEvento());
    }


}