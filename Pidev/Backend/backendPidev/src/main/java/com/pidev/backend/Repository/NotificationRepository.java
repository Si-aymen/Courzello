package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Attachment;
import com.pidev.backend.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
