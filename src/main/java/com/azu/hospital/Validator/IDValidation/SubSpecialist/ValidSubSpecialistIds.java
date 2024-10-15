package com.azu.hospital.Validator.IDValidation.SubSpecialist;



import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = SubSpecialistIdsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSubSpecialistIds {
    String message() default "One or more specialist IDs do not exist in the database.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
