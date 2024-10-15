package com.azu.hospital.notifications.dao;

import com.azu.hospital.notifications.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NotificationDao {

    void createNotification(Notification notification);

    Optional<Notification> getNotificationById(Long id);

    void updateNotification(Notification notification);

    void deleteNotification(Long id);

    Page<Notification> getAllNotifications(Pageable pageable);

    List<Notification> getNotificationByUserId(Long id);

}
