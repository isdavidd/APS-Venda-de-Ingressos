package com.aps.grupo4.event_management_service.entity.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListagemEventoDTO {

    private Long idEvento;
    private String nomeEvento;
    private LocalDateTime dataEvento;
    private String estadoOrUFEvento;
    private String localEvento;

}
