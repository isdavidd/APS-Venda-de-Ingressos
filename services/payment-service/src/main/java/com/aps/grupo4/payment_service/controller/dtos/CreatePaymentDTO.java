package com.aps.grupo4.payment_service.controller.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDTO {
    private Long id;
    private Long usuarioId;
    private BigDecimal valor;
    private String metodoPagamento;
    private Boolean emailEnviado;
    private LocalDateTime dataPagamento;
}
