package com.aps.grupo4.payment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long usuarioId;

    @NotNull(message = "É necessário informar o valor a ser pago.")
    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "metodo_pagamento")
    @NotBlank(message = "O pagamento deve ser efetuado usando um dos métodos disponíveis.")
    private String metodoPagamento;

    @Column(name = "email_enviado")
    private Boolean emailEnviado = false;

    @CreationTimestamp
    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;
}
