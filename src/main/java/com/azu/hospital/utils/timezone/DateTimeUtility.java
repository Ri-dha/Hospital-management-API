package com.azu.hospital.utils.timezone;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {


    public static String formatTime(Instant time) {
        if (time == null) {
            return "Has not been updated";
        }
        ZoneId baghdadZone = ZoneId.of("Asia/Baghdad");
        ZonedDateTime createTime = time.atZone(baghdadZone);
            return createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }
}




//public class DateTimeUtility {
//
//    @Value("${app.zone.id}")
//    private static String timeZone;
//
//    public static String formatTime(Instant time) {
//        if (time == null) {
//            return "Has not been updated";
//        }
//        ZoneId baghdadZone = ZoneId.of(timeZone);
//        ZonedDateTime createTime = time.atZone(baghdadZone);
//        return createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//
//    }
//}
