package com.azu.hospital.notifications.SseServer;

import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.notification.DestinationEnum;

import java.time.Instant;

public record SendNotificationDto<TDto>(

        String title ,

        String content,

        TDto data,

        Instant dateTime

) {
}
