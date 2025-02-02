package com.aps.grupo4.ticket_service.controller;

import java.math.BigDecimal;

public record CreateTicketDTO(BigDecimal preco, Long eventoId) {
}
