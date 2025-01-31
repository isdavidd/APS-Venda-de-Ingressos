package com.aps.grupo4.ticket_service.controller;

import java.math.BigDecimal;

public record UpdateTicketDTO(BigDecimal preco, String status, String nomeComprador, String tipoIngresso) {
}
