package com.azu.hospital.utils.exceptions;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                    WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });

        ErrorResponse response = new ErrorResponse(false, errors.get(0), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    static class ErrorResponse {
        private final boolean status;
        private final String message;
        private final List<String> errors;

        public ErrorResponse(boolean status, String message, List<String> errors) {
            this.status = status;
            this.message = message;
            this.errors = errors;

        }

        public boolean isStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getErrors() {
            return errors;
        }

    }


}