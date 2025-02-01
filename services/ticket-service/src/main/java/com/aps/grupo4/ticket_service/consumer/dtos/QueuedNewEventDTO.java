package com.aps.grupo4.ticket_service.consumer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuedNewEventDTO extends QueuedEventDTO {
    private BigDecimal valorIngressoEvento;

    public QueuedNewEventDTO(Long id, String nomeEvento, Integer capacidadeEvento, BigDecimal valorIngressoEvento) {
        super(id, nomeEvento, capacidadeEvento);
        this.valorIngressoEvento = valorIngressoEvento;
    }
}
