package com.azu.hospital.exceptions;


import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.hibernate.QueryException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@Configuration
@Order
public class DefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseHandle> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Object> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });

        ErrorResponseHandle response =
                new ErrorResponseHandle(errors.stream().findFirst().orElse("Validated Error").toString(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DateTimeExceptions.class)
    public ResponseEntity<ErrorResponseHandle> handleDateTimeExceptions(DateTimeExceptions ex) {
        List<Object> errors = new ArrayList<>();

        ErrorResponseHandle response = new ErrorResponseHandle(Collections.singletonList(ex.getMessage()).toString()
                , errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
//    public ResponseEntity<ErrorResponseHandle> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
//        List<Object> errors = new ArrayList<>();
//        ErrorResponseHandle response = new ErrorResponseHandle(Collections.singletonList(ex.getMessage()).toString()
//                , errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ErrorResponseHandle> handleUnexpectedTypeException(UnexpectedTypeException ex) {

        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorResponseHandle response = new ErrorResponseHandle(errors.stream().findFirst().orElse("Validated Error").toString(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseHandle> handleAccessDeniedException(AccessDeniedException ex) {

        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorResponseHandle response = new ErrorResponseHandle(errors.stream().findFirst().orElse("Validated Error").toString(), errors);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseHandle> handleNullPointerException(NullPointerException ex) {
        String errorMessage = "NullPointerException occurred: " + ex.getMessage();
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle( errorMessage , errors );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseHandle> handleConstraintViolationException(ConstraintViolationException ex
    ) {

        List<Object> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errors.stream().findFirst().orElse("Validated Error").toString(), errors);
        return new ResponseEntity<>(response, HttpStatus.valueOf(400));
    }



    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseHandle> handleBadCredentialsException(BadCredentialsException ex) {
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(" Bad credentials", errors);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseHandle> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = ex.getMessage();
        if (errorMessage.contains("icdcode") && errorMessage.contains("unique")) {
             errorMessage = "Drug ICD Code Should be Unique";
            throw new IllegalStateException(errorMessage);
        }
        List<Object> errors = new ArrayList<>();
            errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errors.stream().findFirst().orElse("Validated Error").toString(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ErrorResponseHandle> handleClassCastException(ClassCastException ex) {
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("Validation Failed : Invalid cast operation. Please check the data types.", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryException.class)
    public ResponseEntity<ErrorResponseHandle> handleQueryException(QueryException ex){
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("Validation Failed", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseHandle> handleUnsupportedOperationException(UnsupportedOperationException ex){
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("Unsupported Request", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseHandle> handleIllegalStateException(IllegalStateException ex){
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("Unsupported Request", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<ErrorResponseHandle> handleSQLException(SQLException ex) {
        String errorMessage = ex.getMessage();
        if (errorMessage.contains("icdcode") && errorMessage.contains("unique")) {
            errorMessage = "Drug ICD Code Should be Null";
        }
        List<Object> errors = new ArrayList<>();
        errors.add(errorMessage);
        ErrorResponseHandle response = new ErrorResponseHandle("Validation Failed", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    protected ResponseEntity<ErrorResponseHandle> handleNonUniqueResultException(NonUniqueResultException ex) {
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("query did not return a unique result: ", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxImageCountExceeded(MaxUploadSizeExceededException ex) {
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle("Exceeded the maximum allowed image count. ", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseHandle> handleAuthenticationException(AuthenticationCredentialsNotFoundException ex) {
        String errorMessage = "Authentication credentials not found. Please log in.";
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errorMessage, errors);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseHandle> handleLockedException(LockedException ex) {
        String errorMessage = "User account is locked";
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errorMessage, errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseHandle> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        String errorMessage = "User not found";
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errorMessage, errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



//    @ExceptionHandler(IllegalStateException.class)// TODO not working
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponseHandle> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
//        String errorMessage = "User not found";
//        List<String> errors = new ArrayList<>();
//        ApiError apiError = new ApiError(
//                request.getContextPath(),
//                ex.getMessage(),
//                HttpStatus.NOT_FOUND.value(),
//                LocalDateTime.now()
//        );
//        ErrorResponseHandle response = new ErrorResponseHandle(errorMessage, errors);
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseHandle> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "There was an error in the entry data";
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ErrorResponseHandle response = new ErrorResponseHandle(errorMessage, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




    static class ErrorResponseHandle {
        private final boolean status = false;
        private final String message;
        private final List<Object> errors;

        public ErrorResponseHandle(String message, List<Object> errors) {
            this.message = message;
            this.errors = errors;

        }

        public boolean isStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public List<Object> getErrors() {
            return errors;
        }

    }
}


