package com.azu.hospital.notifications.dto;

import com.azu.hospital.notifications.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {
    public NotificationDto toDto(Notification notification){
        return new NotificationDto(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getIsSeen(),
                notification.getSender().getId()
        );
    }
}
