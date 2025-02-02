package com.aps.grupo4.ticket_service.entity;

import com.aps.grupo4.ticket_service.config.validation.PrecoMinimo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ingresso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @PrecoMinimo(message = "O ingresso não pode ter preço negativo.")
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
}
