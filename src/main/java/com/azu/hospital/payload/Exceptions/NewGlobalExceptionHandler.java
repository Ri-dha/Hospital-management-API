//package com.azu.hospital.payload.Exceptions;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//
//import org.springframework.validation.FieldError; // Add this import statement
//
//
//@ControllerAdvice
//public class NewGlobalExceptionHandler {
//
////    @ExceptionHandler(BindException.class)
////    public ResponseEntity<ResponseDTO<Object>>
////    	handleBindException(BindException bindException){
////
////        Map<String,String> errorMap=new HashMap<>();
////
////        bindException.getAllErrors().stream().
////        	forEach(objectError -> {
////            errorMap.put(
////            	((FieldError)objectError).getField(),
////                	objectError.getDefaultMessage());
////        });
////
////        return new ResponseEntity(
////             ResponseDTO.builder()
////                .response(errorMap.toString())
////                  .message("Object validation failed")
////                    .build(), HttpStatus.BAD_REQUEST);
////    }
//}