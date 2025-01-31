package com.aps.grupo4.event_management_service.controller;

import com.aps.grupo4.event_management_service.entity.converter.UFEnum;

import java.math.BigDecimal;

public class CreateEventDTO {
    private Long id;
    private Integer capacidadeEvento;
    private BigDecimal valorIngressoEvento;

    public CreateEventDTO(Long id, Integer capacidadeEvento, BigDecimal valorIngressoEvento) {
        this.id = id;
        this.capacidadeEvento = capacidadeEvento;
        this.valorIngressoEvento = valorIngressoEvento;
    }

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
