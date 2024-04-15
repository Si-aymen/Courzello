package com.pidev.backend.Service;

import com.pidev.backend.Entity.Notification;

public interface NotificationService {
    void sendNotification(String userId, Notification notification);

}
