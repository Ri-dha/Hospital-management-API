package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientExperiencingDtoMapper {
  public PatientExperiencingDto entityToDto(PatientExperiencing patientExperiencing){
    return Optional.ofNullable(patientExperiencing)
            .map(p -> new PatientExperiencingDto(
                    p.getId(),
                    p.getExperience(),
                    p.getIsChecked(),
                    p.getNote()

            ))
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "There Is No Data For Returning"
                    )
            );
  }
}
