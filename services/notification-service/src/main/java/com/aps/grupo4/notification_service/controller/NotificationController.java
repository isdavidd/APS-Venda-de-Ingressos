package com.aps.grupo4.notification_service.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {
    private final Map<Long, Sinks.Many<String>> userSinks = new ConcurrentHashMap<>();

    @GetMapping(value = "/notifications/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamNotifications(@PathVariable Long userId) {
        userSinks.putIfAbsent(userId, Sinks.many().multicast().onBackpressureBuffer());
        return userSinks.get(userId).asFlux();
    }

    @PostMapping("/notify/{userId}")
    public ResponseEntity<Void> notify(@PathVariable Long userId, @RequestBody String message) {
        Sinks.Many<String> sink = userSinks.get(userId);
        if (sink != null) {
            sink.tryEmitNext(message);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
