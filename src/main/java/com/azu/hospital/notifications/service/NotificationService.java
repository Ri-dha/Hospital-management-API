package com.azu.hospital.notifications.service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.dao.NotificationDao;
import com.azu.hospital.notifications.dto.NotificationDto;
import com.azu.hospital.notifications.dto.NotificationMapper;
import com.azu.hospital.notifications.entity.Notification;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final BaseUserDao userDao;
    private final NotificationMapper mapper;
    private final NotificationDao notificationDao;


    public NotificationService(
            @Qualifier("BaseUserJpa") BaseUserDao userDao, NotificationMapper mapper,
            @Qualifier("NotificationJpa") NotificationDao notificationDao) {
        this.userDao = userDao;
        this.mapper = mapper;
        this.notificationDao = notificationDao;
    }

    public void createNotification(Long id){

        BaseUser user = userDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "user not found"
                )
        );

        Notification notification = new Notification(
                "title",
                "this is num 1",
                false,
                1L
        );

        notificationDao.createNotification(notification);
    }

    public void deleteNotification(Long notId){
     notificationDao.getNotificationById(notId).orElseThrow(
                () -> new ResourceNotFoundException("not found")

        );

        notificationDao.deleteNotification(notId);
    }

    public Page<NotificationDto> getAllNotifications (Pageable pageable){
       Page<Notification> notificationPage =  notificationDao.getAllNotifications(pageable);
               List<NotificationDto> dtoList = notificationPage
                       .stream()
                       .map(mapper::toDto)
                       .toList();
               return new PageImpl<>(dtoList, pageable, notificationPage.getNumberOfElements());
    }


    public NotificationDto getById(Long id){
        Notification notification = notificationDao.getNotificationById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found")
        );
        return mapper.toDto(notification);

    }

    public List<NotificationDto> getNotificationByUserId(Long id){
         userDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );
        return notificationDao.getNotificationByUserId(id)
                .stream()
                .map(mapper::toDto).collect(Collectors.toList());

    }

}
