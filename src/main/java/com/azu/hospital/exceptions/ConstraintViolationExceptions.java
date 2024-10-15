package com.azu.hospital.exceptions;

import jakarta.validation.ConstraintViolation;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.util.Set;

public class ConstraintViolationExceptions extends ConstraintViolationException {


    public ConstraintViolationExceptions(String message, SQLException root, String constraintName) {
        super(message, root, constraintName);
    }

    public ConstraintViolationExceptions(String message, SQLException root, String sql, String constraintName) {
        super(message, root, sql, constraintName);
    }


    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return getConstraintViolations();
    }
}
