package com.aps.grupo4.ticket_service.consumer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuedCancelledEventDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;

}
