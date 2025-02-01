package com.aps.grupo4.event_management_service.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuedCancelledEventDTO extends QueuedEventoDTO {

    public QueuedCancelledEventDTO(Long id, String nomeEvento, Integer capacidadeEvento) {
        super(id, nomeEvento, capacidadeEvento);
    }
}
