package com.azu.hospital.Validator.ImagesValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;



@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageConstraint {

    String message() default "Invalid image format";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}

