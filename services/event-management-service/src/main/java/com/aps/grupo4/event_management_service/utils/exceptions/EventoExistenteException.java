package com.aps.grupo4.event_management_service.utils.exceptions;

public class EventoExistenteException extends IllegalArgumentException {
    public EventoExistenteException(String message) {
        super(message);
    }
}
