//package com.azu.hospital.Validator.Patient;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Documented
//@Constraint(validatedBy = PatientIdsValidator.class)
//@Target({ElementType.PARAMETER , ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface ValidPatientIds {
//    String message() default "Patient Id Not Exists.";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}
