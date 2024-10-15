package com.azu.hospital.payload;

import java.time.LocalDateTime;

public record ApiError(boolean status ,String path ,
String message , 
int statusCode , 
LocalDateTime localDateTime) {

}
