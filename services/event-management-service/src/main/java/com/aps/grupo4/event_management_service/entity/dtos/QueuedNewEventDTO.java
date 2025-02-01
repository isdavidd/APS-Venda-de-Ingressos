package com.aps.grupo4.event_management_service.entity.dtos;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueuedNewEventDTO extends QueuedEventoDTO {
    private BigDecimal valorIngressoEvento;

    public QueuedNewEventDTO(Long id, String nomeEvento, Integer capacidadeEvento, BigDecimal valorIngressoEvento) {
        super(id, nomeEvento, capacidadeEvento);
        this.valorIngressoEvento = valorIngressoEvento;
    }

}
