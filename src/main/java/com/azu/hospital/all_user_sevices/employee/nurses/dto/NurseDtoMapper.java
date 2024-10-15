package com.azu.hospital.all_user_sevices.employee.nurses.dto;

import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class NurseDtoMapper implements Function<Nurse, NurseDto> {

    @Override
    public NurseDto apply(Nurse nurse) {
        if (nurse == null) {
            return null;
        }
        Long wardId = null;
        String wardName = null;
        if(nurse.getWard() != null) {
            wardId = nurse.getWard().getWardId();
            wardName = nurse.getWard().getName();
        }
        return new NurseDto(
                nurse.getId(),
                nurse.getUsernameSpecial(),
                nurse.getGender(),
                nurse.getMartialStatus(),
                nurse.getImage(),
                nurse.getEmail(),
                nurse.getMobile(),
                nurse.getAddress(),
                nurse.getBirthday(),
                nurse.getEmployeeDate(),
                nurse.getBloodGroup(),
                nurse.getRolesSpecial(),
                nurse.getSpecialist(),
                nurse.getEnabled(),
                nurse.getAccountNonExpired(),
                nurse.getCredentialsNonExpired(),
                nurse.getAccountNonLocked(),
                nurse.getFrontPersonalId(),
                nurse.getBackPersonalId(),
                nurse.getFrontMedicalId(),
                nurse.getBackMedicalId(),
                wardId,
                wardName

        );
    }
}
