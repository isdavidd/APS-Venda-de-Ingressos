package com.aps.grupo4.event_management_service.config.validations;

public class EventoInexistenteException extends RuntimeException {
    public EventoInexistenteException(String message) {
        super(message);
    }
}
