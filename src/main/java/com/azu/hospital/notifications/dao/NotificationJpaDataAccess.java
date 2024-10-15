package com.azu.hospital.notifications.dao;
import com.azu.hospital.notifications.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("NotificationJpa")
public class NotificationJpaDataAccess implements NotificationDao{

    private final NotificationRepository notificationRepoSitory;

    public NotificationJpaDataAccess(NotificationRepository notificationRepoSitory) {
        this.notificationRepoSitory = notificationRepoSitory;
    }

    @Override
    public void createNotification(Notification notification) {
        notificationRepoSitory.save(notification);
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepoSitory.findById(id);
    }

    @Override
    public void updateNotification(Notification notification) {
        notificationRepoSitory.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepoSitory.deleteById(id);
    }

    @Override
    public Page<Notification> getAllNotifications(Pageable pageable) {
        return notificationRepoSitory.findAll(pageable);
    }

    @Override
    public List<Notification> getNotificationByUserId(Long id) {
        return notificationRepoSitory.getNotificationByUserId(id);
    }


}
