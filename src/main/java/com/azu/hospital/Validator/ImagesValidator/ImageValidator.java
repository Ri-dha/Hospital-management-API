package com.azu.hospital.Validator.ImagesValidator;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageValidator implements ConstraintValidator<ImageConstraint, MultipartFile> {

    private final List<String> allowedContentTypes = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        return allowedContentTypes.contains(file.getContentType());
    }
}