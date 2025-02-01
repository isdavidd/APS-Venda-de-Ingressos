package com.aps.grupo4.notification_service.service;

import com.aps.grupo4.notification_service.controller.dtos.CreateNotificationDTO;
import com.aps.grupo4.notification_service.entity.Notification;
import com.aps.grupo4.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Long createNotification(CreateNotificationDTO createNotificationDTO){
        var entity = new Notification(
                createNotificationDTO.getId(),
                createNotificationDTO.getUsuarioId(),
                createNotificationDTO.getMessage()
        );

        Notification notification = notificationRepository.save(entity);
        return notification.getId();
    }
}
