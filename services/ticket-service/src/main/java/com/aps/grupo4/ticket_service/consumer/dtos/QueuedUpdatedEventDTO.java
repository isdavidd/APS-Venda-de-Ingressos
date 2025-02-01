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
public class QueuedUpdatedEventDTO extends QueuedEventDTO {
    private BigDecimal valorIngressoEvento;
    private Integer diferencaCapacidade;

    public QueuedUpdatedEventDTO(Long id, String nomeEvento, Integer capacidadeEvento, BigDecimal valorIngressoEvento ,Integer diferencaCapacidade) {
        super(id, nomeEvento, capacidadeEvento);
        this.valorIngressoEvento = valorIngressoEvento;
        this.diferencaCapacidade = diferencaCapacidade;
    }
}
