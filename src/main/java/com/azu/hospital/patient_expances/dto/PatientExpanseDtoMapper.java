package com.azu.hospital.patient_expances.dto;

import com.azu.hospital.patient_expances.entity.PatientExpanse;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class PatientExpanseDtoMapper implements Function<PatientExpanse, PatientExpanseDto> {
    @Override
    public PatientExpanseDto apply(PatientExpanse patientExpanse) {
        return new PatientExpanseDto(
             patientExpanse.getId(),
                patientExpanse.getConsumables().getConsumableId(),
                patientExpanse.getConsumables().getBarcode(),
                patientExpanse.getItemName(),
                patientExpanse.getItemCount(),
                patientExpanse.getNote(),
                patientExpanse.getCreatedAt(),
                patientExpanse.getUpdateAt()
        );
    }
}
