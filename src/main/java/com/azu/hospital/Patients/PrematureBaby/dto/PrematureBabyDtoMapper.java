package com.azu.hospital.Patients.PrematureBaby.dto;

import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PrematureBabyDtoMapper implements Function<PrematureBaby, PrematureBabyDto> {
    @Override
    public PrematureBabyDto apply(PrematureBaby prematureBaby) {
        return new PrematureBabyDto(
                prematureBaby.getId(),
                prematureBaby.getName(),
                prematureBaby.getHeadCircumference(),
                prematureBaby.getWeight(),
                prematureBaby.getHeight(),
                prematureBaby.getPatient().getId(),
                prematureBaby.getPatient().getPatientData().getFullName(),
                prematureBaby.getPatient().getPatientContact().getMobile(),
                prematureBaby.getPatient().getPatientContact().getRelativeMobile(),
                prematureBaby.getPrematureBabyDate().getBirthDate(),
                prematureBaby.getPrematureBabyDate().getAge(),
                prematureBaby.getWard().getWardId(),
                prematureBaby.getWard().getName(),
                prematureBaby.getBed().getId(),
                prematureBaby.getBed().getBedNumber(),
                prematureBaby.getCreatedBy() == null ? null : prematureBaby.getCreatedBy(),
                prematureBaby.getLastModifiedBy() == null ? null : prematureBaby.getLastModifiedBy(),
                prematureBaby.getIsDischarged(),
                prematureBaby.getGender(),
                prematureBaby.getDoctor().getId()==null?null:prematureBaby.getDoctor().getId(),
                prematureBaby.getDoctor().getUsernameSpecial()==null?null:prematureBaby.getDoctor().getUsernameSpecial()
        );
    }
}
