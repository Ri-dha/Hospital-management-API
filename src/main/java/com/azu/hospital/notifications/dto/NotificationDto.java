package com.azu.hospital.notifications.dto;

public record NotificationDto(
        Long id,
        String title,
        String content,
        Boolean isSeen,
        Long userId

){


}
