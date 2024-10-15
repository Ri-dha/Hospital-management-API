package com.azu.hospital.Patients.patientDrugs.dto;

import com.azu.hospital.Patients.patientDrugs.dao.PatientDrug;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Service
public class PatientDrugDtoMapper implements Function<PatientDrug, PatientDrugDto> {
  @Override
  public PatientDrugDto apply(PatientDrug patientDrug) {
    return Optional.ofNullable(patientDrug)
            .map(p -> new PatientDrugDto(
                    p.getDrugId(),
                    p.getDrug(),
                    p.getDose(),
                    p.getTime(),
                    p.getRoa(),
                    p.getCount(),
                    p.getNote()

            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There Is No Data For Returning"
                    )
            );
  }
}
