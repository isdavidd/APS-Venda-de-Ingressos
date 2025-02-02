package com.aps.grupo4.event_management_service.entity;


import com.aps.grupo4.event_management_service.entity.converter.UFConverter;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nomeEvento;

    @Column(name = "data", nullable = false)
    private LocalDateTime dataEvento;

    @Column(name = "uf", nullable = false)
    @Convert(converter = UFConverter.class)
    private UFEnum ufEvento;

    @Column(name = "local", nullable = false, length = 255)
    private String localEvento;

    @Column(name = "valor_ingresso", nullable = false)
    private BigDecimal valorIngressoEvento;

    @Column(name = "capacidade", nullable = false)
    private Integer capacidadeEvento;

    @Column(name = "descricao", length = 255)
    private String descricaoEvento;
}
