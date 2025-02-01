package com.aps.grupo4.event_management_service.entity.dtos;

import lombok.*;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueuedUpdatedEventDTO extends QueuedEventoDTO {
    private BigDecimal valorIngressoEvento;
    private Integer diferencaCapacidade;

    public QueuedUpdatedEventDTO(Long id, String nomeEvento, Integer capacidadeEvento, BigDecimal valorIngressoEvento ,Integer diferencaCapacidade) {
        super(id, nomeEvento, capacidadeEvento);
        this.valorIngressoEvento = valorIngressoEvento;
        this.diferencaCapacidade = diferencaCapacidade;
    }
}
