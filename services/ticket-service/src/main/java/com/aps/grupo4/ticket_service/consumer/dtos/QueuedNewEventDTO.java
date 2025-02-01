package com.aps.grupo4.ticket_service.consumer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuedNewEventDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;
    private BigDecimal valorIngressoEvento;

}
