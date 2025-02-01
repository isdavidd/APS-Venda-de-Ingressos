package com.aps.grupo4.ticket_service.consumer.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuedUpdatedEventDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;
    private BigDecimal valorIngressoEvento;
    private Integer diferencaCapacidade;

}
