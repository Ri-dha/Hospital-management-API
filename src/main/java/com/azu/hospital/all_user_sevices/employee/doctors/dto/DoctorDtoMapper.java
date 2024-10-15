package com.azu.hospital.all_user_sevices.employee.doctors.dto;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DoctorDtoMapper implements Function<Doctor, DoctorDto> {

    @Override
    public DoctorDto apply(Doctor doctor) {
        Long wardId = null;
        String wardName = null;
        if(doctor.getWard() != null) {
            wardId = doctor.getWard().getWardId();
            wardName = doctor.getWard().getName();
        }
        return new DoctorDto(
                doctor.getId(),
                doctor.getUsernameSpecial(),
                doctor.getGender(),
                doctor.getMartialStatus(),
                doctor.getImage(),
                doctor.getEmail(),
                doctor.getMobile(),
                doctor.getAddress(),
                doctor.getBirthday(),
                doctor.getEmployeeDate(),
                doctor.getBloodGroup(),
                doctor.getRolesSpecial(),
                doctor.getSpecialist(),
                doctor.getSubSpecialist(),
                doctor.getEnabled(),
                doctor.getAccountNonExpired(),
                doctor.getCredentialsNonExpired(),
                doctor.getAccountNonLocked(),
                doctor.getFrontPersonalId(),
                doctor.getBackPersonalId(),
                doctor.getFrontMedicalId(),
                doctor.getBackMedicalId(),
                wardId,
                wardName
        );
    }
}

