package com.aps.grupo4.ticket_service.consumer;

import com.aps.grupo4.ticket_service.consumer.dtos.QueuedCancelledEventDTO;
import com.aps.grupo4.ticket_service.consumer.dtos.QueuedNewEventDTO;
import com.aps.grupo4.ticket_service.consumer.dtos.QueuedUpdatedEventDTO;
import com.aps.grupo4.ticket_service.entity.Ticket;
import com.aps.grupo4.ticket_service.repository.TicketRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TicketConsumer {

    private final TicketRepository ticketRepository;

    public TicketConsumer(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @RabbitListener(queues = "evento_criado", concurrency = "1-1")
    @Transactional
    public void processarEventoCriado(byte[] message) {
        processarMensagemEventoCriado(message);
    }

    @RabbitListener(queues = "capacidade_aumentada", concurrency = "1-1")
    @Transactional
    public void processarCapacidadeEventoAumentada(byte[] message) {
        processarMensagemEventoCapacidadeAumentada(message);
    }

    @RabbitListener(queues = "capacidade_reduzida", concurrency = "1-1")
    @Transactional
    public void processarCapacidadeEventoReduzida(byte[] message) {
        processarMensagemEventoCapacidadeReduzida(message);
    }

    @RabbitListener(queues = "evento_cancelado", concurrency = "1-1")
    @Transactional
    public void processarEventoCancelado(byte[] message) {
        processarMensagemEventoCancelado(message);
    }

    private void processarMensagemEventoCriado(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            QueuedNewEventDTO queuedNewEventDTO = objectMapper.readValue(message, QueuedNewEventDTO.class);

            List<Ticket> ingressos = new ArrayList<>();

            for (int i = 0; i < queuedNewEventDTO.getCapacidadeEvento(); i++) {
                ingressos.add(Ticket.builder()
                        .eventoId(queuedNewEventDTO.getId())
                        .preco(queuedNewEventDTO.getValorIngressoEvento())
                        .build()
                );
            }

            ticketRepository.saveAll(ingressos);
            System.out.println("✅ " + queuedNewEventDTO.getCapacidadeEvento() + " ingressos criados!");

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processarMensagemEventoCapacidadeAumentada(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            QueuedUpdatedEventDTO queuedUpdatedEventDTO = objectMapper.readValue(message, QueuedUpdatedEventDTO.class);

            List<Ticket> ingressos = new ArrayList<>();

            for (int i = 0; i < queuedUpdatedEventDTO.getCapacidadeEvento(); i++) {
                ingressos.add(Ticket.builder()
                        .eventoId(queuedUpdatedEventDTO.getId())
                        .preco(queuedUpdatedEventDTO.getValorIngressoEvento())
                        .build()
                );
            }

            ticketRepository.saveAll(ingressos);
            log.info("✅ Evento com ID {} aumentou sua capacidade. {} novos ingressos foram criados", queuedUpdatedEventDTO.getId(), queuedUpdatedEventDTO.getDiferencaCapacidade());

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processarMensagemEventoCapacidadeReduzida(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            QueuedUpdatedEventDTO queuedUpdatedEventDTO = objectMapper.readValue(message, QueuedUpdatedEventDTO.class);

            ticketRepository.registrosAfetados(queuedUpdatedEventDTO.getId(), queuedUpdatedEventDTO.getDiferencaCapacidade());

            log.info("❌ Evento com ID {} diminuiu sua capacidade. {} ingressos foram apagados", queuedUpdatedEventDTO.getId(), queuedUpdatedEventDTO.getDiferencaCapacidade());

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processarMensagemEventoCancelado(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            QueuedCancelledEventDTO queuedCancelledEventDTO = objectMapper.readValue(message, QueuedCancelledEventDTO.class);

            ticketRepository.deleteByEventoId(queuedCancelledEventDTO.getId());
            log.info("❌ Evento com ID {} cancelado. {} ingressos foram deletados.", queuedCancelledEventDTO.getId(), queuedCancelledEventDTO.getCapacidadeEvento());

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
