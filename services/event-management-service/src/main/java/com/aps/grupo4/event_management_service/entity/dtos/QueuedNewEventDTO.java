package com.aps.grupo4.event_management_service.entity.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueuedNewEventDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;
    private BigDecimal valorIngressoEvento;

}
