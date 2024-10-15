package com.azu.hospital.notifications.dao;

import com.azu.hospital.notifications.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE n.sender.id = :id order by n.createdAt ")
    List<Notification> getNotificationByUserId(@Param("id") Long id);

}
