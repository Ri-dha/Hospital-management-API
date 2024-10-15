package com.azu.hospital.Patients.patientDoctors.dto;

import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDoctorDto {

    private Long id;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private String doctorImage;

    private PatientDoctorState state;

    public PatientDoctorDto(
            Long id,
            Long patientId,
            String patientName,
            Long doctorId,
            String doctorName,
            String doctorImage,
            PatientDoctorState state
        ) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorImage = doctorImage;
        this.state = state;
    }

    public PatientDoctorDto() {
    }
}
