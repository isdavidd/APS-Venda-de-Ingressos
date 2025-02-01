package com.aps.grupo4.event_management_service.entity.dtos;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueuedCancelledEventDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;

}
