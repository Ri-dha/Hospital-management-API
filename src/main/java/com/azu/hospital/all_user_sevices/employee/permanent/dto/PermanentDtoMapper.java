package com.azu.hospital.all_user_sevices.employee.permanent.dto;

import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class PermanentDtoMapper implements Function<Permanent, PermanentDto> {


    @Override
    public PermanentDto apply(Permanent permanent) {
        if (permanent == null) {
            return null;
        }
        return new PermanentDto(
                permanent.getId(),
                permanent.getUsernameSpecial(),
                permanent.getGender(),
                permanent.getMartialStatus(),
                permanent.getImage(),
                permanent.getEmail(),
                permanent.getMobile(),
                permanent.getAddress(),
                permanent.getBirthday(),
                permanent.getEmployeeDate(),
                permanent.getBloodGroup(),
                permanent.getRolesSpecial(),
                permanent.getSpecialist(),
                permanent.getEnabled(),
                permanent.getAccountNonExpired(),
                permanent.getCredentialsNonExpired(),
                permanent.getAccountNonLocked(),
                permanent.getFrontPersonalId(),
                permanent.getBackPersonalId(),
                permanent.getFrontMedicalId(),
                permanent.getBackMedicalId()
        );
    }
}
