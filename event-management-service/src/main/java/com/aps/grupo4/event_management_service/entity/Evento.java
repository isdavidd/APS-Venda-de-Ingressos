package com.aps.grupo4.event_management_service.entity;


import com.aps.grupo4.event_management_service.config.validations.DataMinima;
import com.aps.grupo4.event_management_service.entity.converter.UFConverter;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
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

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O evento precisar ter nome")
    private String nomeEvento;

    @Column(name = "data", nullable = false)
    @DataMinima
    @NotNull(message = "O evento precisa ter data")
    private LocalDateTime dataEvento;

    @Column(name = "endereco", nullable = false)
    @NotBlank(message = "O evento precisa ter endereço")
    private String enderecoEvento;

    @Column(name = "uf", nullable = false)
    @Convert(converter = UFConverter.class)
    private UFEnum ufEvento;

    @Column(name = "local", nullable = false)
    @NotNull(message = "O evento precisa ter local")
    private String localEvento;

    @Column(name = "valor_ingresso", nullable = false)
    @NotNull(message = "O valor do ingresso não pode ser nulo")
    @Positive(message = "O valor do ingresso deve ser maior que 0")
    private BigDecimal valorIngressoEvento;

    @Column(name = "capacidade", nullable = false)
    @Min(value = 1, message = "A capacidade do evento deve ser maior que 0")
    private Integer capacidadeEvento;

    @Column(name = "descricao")
    private String descricaoEvento;
}
