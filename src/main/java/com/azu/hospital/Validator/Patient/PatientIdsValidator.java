//package com.azu.hospital.Validator.Patient;
//
//
//import com.azu.hospital.Patients.Patient.dao.PatientDao;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//public class PatientIdsValidator implements ConstraintValidator<ValidPatientIds, Long> {
//
//
//    private final PatientDao patientDao;
//
//    public PatientIdsValidator(@Qualifier("patientRepo") PatientDao patientDao) {
//        this.patientDao = patientDao;
//    }
//
//
//
//    @Override
//    public void initialize(ValidPatientIds constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(Long value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return false; // Allow null values
//        }
//
//
//        return patientDao.existsPatientById(value);
//
//
//        // If it's neither an Integer nor a Set, then it's an invalid type
////        return false;
//    }
//}
