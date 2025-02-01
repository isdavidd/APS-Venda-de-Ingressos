package com.aps.grupo4.notification_service.controller.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateNotificationDTO {
    private Long id;

    private Long usuarioId;

    @NotBlank
    private String message;
}
