package com.aps.grupo4.ticket_service.entity;

import com.aps.grupo4.ticket_service.config.validation.PrecoMinimo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ingresso")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_evento", nullable = false)
    private Long eventoId;

    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column(name = "data_compra")
    private Instant dataCompra;

    @NotNull(message = "O ingresso precisa possuir um preço.")
    @PrecoMinimo
    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "tipo_ingresso")
    private String tipoIngresso = "Inteira";

    @Column(name = "status", length = 15)
    private String status = "Disponivel";

    @Column(name = "nome_comprador", length = 255)
    private String nomeComprador;

    @CreationTimestamp
    @Column(name = "creation_time_stamp", updatable = false)
    private Instant creationTimeStamp;

    @UpdateTimestamp
    @Column(name = "update_time_stamp")
    private Instant updateTimeStamp;

    public Ticket() {}


    public Ticket(Long eventoId, Long usuarioId, Instant dataCompra, BigDecimal preco, String nomeComprador) {
        this.eventoId = eventoId;
        this.usuarioId = usuarioId;
        this.dataCompra = dataCompra;
        this.preco = preco;
        this.nomeComprador = nomeComprador;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Instant getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Instant dataCompra) {
        this.dataCompra = dataCompra;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public Instant getUpdateTimeStamp() {
        return updateTimeStamp;
    }
}
