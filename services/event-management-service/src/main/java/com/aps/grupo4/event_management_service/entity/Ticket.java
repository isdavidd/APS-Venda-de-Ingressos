package com.aps.grupo4.event_management_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "ingresso")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "tipo_ingresso")
    private String tipoIngresso = "Inteira";

    @Column(name = "status", length = 15)
    private String status = "Disponivel";

    @Column(name = "nome_comprador", length = 255)
    private String nomeComprador;

    @Column(name = "creation_time_stamp", updatable = false)
    private Instant creationTimeStamp;

    @Column(name = "update_time_stamp")
    private Instant updateTimeStamp;
}
