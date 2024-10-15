package com.azu.hospital.Validator.DateValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<DatePattern, String> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(DatePattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Empty values will be handled by @NotBlank or @NotEmpty annotations
        }

        try {
            LocalDate.parse(value, DATE_FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}