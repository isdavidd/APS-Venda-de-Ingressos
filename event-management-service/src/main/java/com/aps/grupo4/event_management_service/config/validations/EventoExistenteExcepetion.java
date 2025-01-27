package com.aps.grupo4.event_management_service.config.validations;

public class EventoExistenteExcepetion extends IllegalArgumentException {
    public EventoExistenteExcepetion(String message) {
        super(message);
    }
}
