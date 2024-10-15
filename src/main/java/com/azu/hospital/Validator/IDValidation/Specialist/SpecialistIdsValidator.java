//package com.azu.hospital.Validator.IDValidation.Specialist;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.util.Set;
//
//public class SpecialistIdsValidator implements ConstraintValidator<ValidSpecialistIds, Object> {
//
//    private final SpecialistRepository specialistRepository;
//
//    public SpecialistIdsValidator(SpecialistRepository specialistRepository) {
//        this.specialistRepository = specialistRepository;
//    }
//
//    @Override
//    public void initialize(ValidSpecialistIds constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(Object value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true; // Allow null values
//        }
//
//
//        try {
//            value = Integer.parseInt((String) value);
//
//        } catch (Exception e) {
//            value = value;
//        }
//
//        if (value instanceof Integer) {
//            System.out.println(specialistRepository.existsById((Integer) value));
//            return specialistRepository.existsById((Integer) value);
//        } else if (value instanceof Set) {
//
//            Set<Integer> specialistIds = (Set<Integer>) value;
//            System.out.println(specialistIds);
//
//            return specialistIds.isEmpty() || specialistRepository.existsByIdIn(specialistIds);
//        }
//
//        // If it's neither an Integer nor a Set, then it's an invalid type
//        return false;
//    }
//}
