package com.azu.hospital.Validator.IDValidation.Specialist;



import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSpecialistIds {
    String message() default "One or more specialist IDs do not exist in the database.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
