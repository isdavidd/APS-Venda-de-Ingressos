package com.aps.grupo4.event_management_service.entity.dtos;

import com.aps.grupo4.event_management_service.utils.customValidators.DataMinima;
import com.aps.grupo4.event_management_service.utils.deserializers.UFSiglaOuEstadoDeserializer;
import com.aps.grupo4.event_management_service.utils.deserializers.WhiteSpaceRemovalDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDTO {

    private Long idEvento;

    @NotBlank(message = "O evento precisar ter nome")
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String nomeEvento;

    @DataMinima
    @NotNull(message = "O evento precisa ter data")
    private LocalDateTime dataEvento;

    @NotBlank(message = "O evento precisa ter endereço")
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String enderecoEvento;

    @NotBlank(message = "O evento precisa do nome do estado ou uma Unidade Federativa")
    @JsonDeserialize(using = UFSiglaOuEstadoDeserializer.class)
    @Length(min = 2)
    private String estadoOrUF;

    @NotBlank(message = "O evento precisa ter local")
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String localEvento;

    @NotNull(message = "O valor do ingresso não pode ser nulo")
    @Positive(message = "O valor do ingresso deve ser maior que 0")
    private BigDecimal valorIngressoEvento;

    @Min(value = 1, message = "A capacidade do evento deve ser maior que 0")
    private Integer capacidadeEvento;

    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    private String descricaoEvento;
}
