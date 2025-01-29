package com.aps.grupo4.event_management_service.config.validations.exceptions;

public class EventoExistenteException extends IllegalArgumentException {
    public EventoExistenteException(String message) {
        super(message);
    }
}
