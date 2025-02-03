package com.aps.grupo4.payment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@Builder
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

    @Builder.Default
    @Column(name = "email_enviado")
    private Boolean emailEnviado = false;

    @CreationTimestamp
    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public Payment(){}

    public Payment(Long id, Long usuarioId, BigDecimal valor, String metodoPagamento, Boolean emailEnviado, LocalDateTime dataPagamento) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.emailEnviado = emailEnviado;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public @NotNull(message = "É necessário informar o valor a ser pago.") BigDecimal getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "É necessário informar o valor a ser pago.") BigDecimal valor) {
        this.valor = valor;
    }

    public @NotBlank(message = "O pagamento deve ser efetuado usando um dos métodos disponíveis.") String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(@NotBlank(message = "O pagamento deve ser efetuado usando um dos métodos disponíveis.") String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Boolean getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(Boolean emailEnviado) {
        this.emailEnviado = emailEnviado;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
