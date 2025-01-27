package com.aps.grupo4.event_management_service.entity;


import com.aps.grupo4.event_management_service.config.validations.DataMinima;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private int id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "O evento precisar ter nome!")
    private String nomeEvento;

    @Column(name = "data", nullable = false)
    @DataMinima
    @NotNull(message = "O evento precisa ter data!")
    private LocalDateTime dataEvento;

    @Column(name = "local", nullable = false)
    @NotNull(message = "O evento precisa ter local!")
    private String localEvento;

    @Column(name = "valor_ingresso", nullable = false)
    @NotNull(message = "O valor do ingresso não pode ser nulo!")
    @Positive(message = "O valor do ingresso deve ser maior que 0!")
    private BigDecimal valorIngressoEvento;

    @Column(name = "capacidade", nullable = false)
    @Min(value = 1, message = "A capacidade do evento deve ser maior que 0")
    private Integer capacidadeEvento;

    @Column(name = "descricao")
    private String descricaoEvento;
}
