package com.azu.hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DateTimeExceptions extends RuntimeException {
    public DateTimeExceptions(String message){
        super(message);
    }
}
