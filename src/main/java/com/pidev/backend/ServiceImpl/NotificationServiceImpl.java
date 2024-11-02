package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Notification;
import com.pidev.backend.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service

public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendNotification(String userId, Notification notification) {
        // Envoie la notification à l'utilisateur spécifié via WebSocket
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", notification);
    }
}
