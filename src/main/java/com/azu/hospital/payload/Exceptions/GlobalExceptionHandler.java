//package com.azu.hospital.payload.Exceptions;
//
//
//import java.net.http.HttpHeaders;
//import java.security.SignatureException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageConversionException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import com.azu.hospital.Helpers.ResponseHelper;
//import com.fasterxml.jackson.databind.JsonMappingException;
//
//import jakarta.validation.UnexpectedTypeException;
//import org.springframework.web.servlet.ModelAndView;
//
//@Configuration
//@Order
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(UnexpectedTypeException.class)
//    public ResponseEntity<ErrorResponse> handleUnexpectedTypeException(UnexpectedTypeException ex, WebRequest request) {
////vds
//        List<String> errors = new ArrayList<>();
//        errors.add(ex.getMessage());
//
//        ErrorResponse response = new ErrorResponse(false, errors.get(0), errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//
//    @ExceptionHandler(SignatureException.class)
//    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException ex, WebRequest request) {
//        List<String> errors = new ArrayList<>();
//        errors.add(ex.getMessage());
//
//        ErrorResponse response = new ErrorResponse(false, errors.get(0), errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleSignatureException(UsernameNotFoundException ex, WebRequest request) {
////vds
//        List<String> errors = new ArrayList<>();
//        errors.add(ex.getMessage());
//
//        ErrorResponse response = new ErrorResponse(false, errors.get(0), errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
//            WebRequest request) {
//        List<String> errors = new ArrayList<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        });
//
//        ErrorResponse response = new ErrorResponse(false, errors.get(0), errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    static class ErrorResponse {
//        private final boolean status;
//        private final String message;
//        private final List<String> errors;
//
//        public ErrorResponse(boolean status, String message, List<String> errors) {
//            this.status = status;
//            this.message = message;
//            this.errors = errors;
//
//        }
//
//        public boolean isStatus() {
//            return status;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public List<String> getErrors() {
//            return errors;
//        }
//
//    }
//
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseBody
//    public ResponseHelper handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//
//        String errorMessage = ex.getMostSpecificCause().getMessage();
//
//        String acceptedEnumValues = extractAcceptedEnumValues(errorMessage);
//
//        String responseMessage = "Error: Invalid input format. Accepted values for params are " + acceptedEnumValues;
//
//        return new ResponseHelper(false, responseMessage, null);
//    }
//
//
//    @ExceptionHandler(HttpMessageConversionException.class)
//    @ResponseBody
//    public ResponseHelper handleHttpMessageNotReadableExceptionFile(HttpMessageConversionException ex) {
//
//        String errorMessage = ex.getMostSpecificCause().getMessage();
//        System.out.println(errorMessage);
//        String acceptedFileValues = extractAcceptedFileValues(errorMessage);
//
//        String responseMessage = "file format incorrect " + acceptedFileValues ;
//
//        return new ResponseHelper(false, responseMessage, null);
//    }
//
//    private String extractAcceptedFileValues(String errorMessage) {
//        // Extract the accepted enum values from the error message using string manipulation
//        int startIndex = errorMessage.indexOf("[\"")+2;
//        int endIndex = errorMessage.indexOf("\"]")-1;
//        if (startIndex >= 0 && endIndex > startIndex) {
//            return errorMessage.substring(startIndex, endIndex + 1);
//        }
//        return "unknown";
//    }
//    private String extractAcceptedEnumValues(String errorMessage) {
//        // Extract the accepted enum values from the error message using string manipulation
//        int startIndex = errorMessage.indexOf("[");
//        int endIndex = errorMessage.indexOf("]");
//        if (startIndex >= 0 && endIndex > startIndex) {
//            return errorMessage.substring(startIndex, endIndex + 1);
//        }
//        return "unknown";
//    }
//
//
//
//
//
//
//
//}
