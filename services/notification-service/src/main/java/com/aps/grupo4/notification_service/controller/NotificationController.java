package com.aps.grupo4.notification_service.controller;

import com.aps.grupo4.notification_service.controller.dtos.CreateNotificationDTO;
import com.aps.grupo4.notification_service.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody CreateNotificationDTO createNotificationDTO){
        try{
            var notificationId = notificationService.createNotification(createNotificationDTO);
            return ResponseEntity.ok().body(notificationId);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<Object> getNotificationsByUserid(@PathVariable("UserId") Long UserId){
        try{

        }
    }
}
