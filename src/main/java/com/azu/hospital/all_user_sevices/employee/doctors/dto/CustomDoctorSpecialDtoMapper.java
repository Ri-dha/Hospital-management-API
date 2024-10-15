package com.azu.hospital.all_user_sevices.employee.doctors.dto;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomDoctorSpecialDtoMapper implements Function<Doctor,CustomDoctorSpecialDto> {
    @Override
    public CustomDoctorSpecialDto apply(Doctor doctor) {
        return new CustomDoctorSpecialDto(
                doctor.getId(),
                doctor.getUsernameSpecial(),
                doctor.getSpecialist(),
                doctor.getAddress(),
                doctor.getImage()
        );
    }
}
