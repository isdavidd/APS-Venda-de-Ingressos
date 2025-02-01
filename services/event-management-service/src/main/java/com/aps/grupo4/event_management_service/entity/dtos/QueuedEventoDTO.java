package com.aps.grupo4.event_management_service.entity.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class QueuedEventoDTO {
    private Long id;
    private String nomeEvento;
    private Integer capacidadeEvento;
}
