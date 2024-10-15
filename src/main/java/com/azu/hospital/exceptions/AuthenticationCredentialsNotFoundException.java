package com.azu.hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationCredentialsNotFoundException extends RuntimeException{

    public AuthenticationCredentialsNotFoundException(String message) {
        super(message);
    }
}
