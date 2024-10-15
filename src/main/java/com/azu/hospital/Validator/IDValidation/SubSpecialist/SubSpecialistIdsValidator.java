package com.azu.hospital.Validator.IDValidation.SubSpecialist;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;



public class SubSpecialistIdsValidator implements ConstraintValidator<ValidSubSpecialistIds, Object> {

    




    @Override
    public void initialize(ValidSubSpecialistIds constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values
        }

        try {
            value = Integer.parseInt((String) value);

        } catch (Exception e) {
            value = value;
        }

        if (value instanceof Integer) {

            Integer specialistId = (Integer) value;

        } else if (value instanceof Set) {
            Set<Integer> specialistIds = (Set<Integer>) value;
            return specialistIds.isEmpty() ;
        }

        return false;
    }
}
