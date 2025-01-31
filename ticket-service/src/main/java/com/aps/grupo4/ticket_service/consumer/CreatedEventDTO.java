package com.aps.grupo4.ticket_service.consumer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreatedEventDTO {
    private Long id;
    private Integer capacidadeEvento;
    private BigDecimal valorIngressoEvento;

//    public CreatedEventDTO() {
//    }
//
//    public CreatedEventDTO(Long id, Integer capacidadeEvento, BigDecimal valorIngressoEvento) {
//        this.id = id;
//        this.capacidadeEvento = capacidadeEvento;
//        this.valorIngressoEvento = valorIngressoEvento;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacidadeEvento() {
        return capacidadeEvento;
    }

    public void setCapacidadeEvento(Integer capacidadeEvento) {
        this.capacidadeEvento = capacidadeEvento;
    }

    public BigDecimal getValorIngressoEvento() {
        return valorIngressoEvento;
    }

    public void setValorIngressoEvento(BigDecimal valorIngressoEvento) {
        this.valorIngressoEvento = valorIngressoEvento;
    }
}
