package com.azu.hospital.Patients.patientDoctors.dto;

import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import org.springframework.stereotype.Service;

@Service
public class PatientDoctorDtoMapper {

    public PatientDoctorDto toDto(PatientDoctor patientDoctor){
        return new PatientDoctorDto(
                patientDoctor.getId(),
                patientDoctor.getPatient().getId(),
                patientDoctor.getPatient().getPatientData().getFullName(),
                patientDoctor.getDoctor().getId(),
                patientDoctor.getDoctor().getUsername(),
                patientDoctor.getDoctor().getImage(),
                patientDoctor.getState()
        );
    }
}
