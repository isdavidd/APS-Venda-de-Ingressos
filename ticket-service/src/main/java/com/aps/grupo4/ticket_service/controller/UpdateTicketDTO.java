package com.aps.grupo4.ticket_service.controller;

public record UpdateTicketDTO(double price, String status, String buyerName, String paymentMethod, String ticketType) {
}
