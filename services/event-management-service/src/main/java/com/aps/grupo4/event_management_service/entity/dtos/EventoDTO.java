package com.aps.grupo4.event_management_service.entity.dtos;

import com.aps.grupo4.event_management_service.utils.customValidators.DataMinima;
import com.aps.grupo4.event_management_service.utils.deserializers.UFSiglaOuEstadoDeserializer;
import com.aps.grupo4.event_management_service.utils.deserializers.WhiteSpaceRemovalDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO para representar um evento")
public class EventoDTO {

    @Schema(description = "ID do evento", example = "1")
    private Long idEvento;

    @NotBlank(message = "O evento precisar ter nome")
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @Length(max = 255, message = "O tamanho não pode exceder 255 caracteres")
    @Schema(description = "Nome do evento", example = "Show da Taylor Swift")
    private String nomeEvento;

    @DataMinima
    @NotNull(message = "O evento precisa ter data")
    @Schema(description = "Data e hora do evento", example = "2024-12-25T20:00:00")
    private LocalDateTime dataEvento;

    @NotBlank(message = "O evento precisa do nome do estado ou uma Unidade Federativa")
    @JsonDeserialize(using = UFSiglaOuEstadoDeserializer.class)
    @Length(min = 2, message = "O campo de Estado/UF deve ter pelo menos 2 caracteres")
    @Schema(description = "Estado ou UF do evento", example = "SP")
    private String estadoOrUFEvento;

    @NotBlank(message = "O evento precisa ter local")
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @Length(max = 255, message = "O tamanho não pode exceder 255 caracteres")
    @Schema(description = "Local do evento", example = "Allianz Parque")
    private String localEvento;

    @NotNull(message = "O valor do ingresso não pode ser nulo")
    @Positive(message = "O valor do ingresso deve ser maior que 0")
    @Schema(description = "Valor do ingresso do evento", example = "250.00")
    private BigDecimal valorIngressoEvento;

    @Min(value = 1, message = "A capacidade do evento deve ser maior que 0")
    @Schema(description = "Capacidade máxima do evento", example = "50000")
    private Integer capacidadeEvento;

    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @Length(max = 255, message = "O tamanho não pode exceder 255 caracteres")
    @Schema(description = "Descrição do evento", example = "Show internacional da Taylor Swift")
    private String descricaoEvento;
}
