package com.aps.grupo4.notification_service.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {
    private final Queue<Sinks.Many<String>> sinks = new ConcurrentLinkedQueue<>();

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamNotifications() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        sinks.offer(sink); // Adiciona o novo sink na fila
        return sink.asFlux().doFinally(signalType -> sinks.remove(sink));
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> notify(@RequestBody String message) {
        System.out.println("Mensagem recebida: " + message);
        for (Sinks.Many<String> sink : sinks) {
            sink.tryEmitNext(message); // Emite a mensagem para todos os sinks
        }
        return ResponseEntity.ok().build();
    }
}
