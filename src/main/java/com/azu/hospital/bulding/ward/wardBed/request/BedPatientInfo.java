package com.azu.hospital.bulding.ward.wardBed.request;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.Gender;
import lombok.Data;

@Data
public class BedPatientInfo {

    private Long patientId;

    private String patientName;

    private String doctorName;

    private String wardName;

    private Gender gender;

    private String age;


    public BedPatientInfo(Patient patient) {
        this.patientId = patient.getId();
        this.patientName = patient.getPatientData().getFullName();
        this.doctorName = patient.getDoctor().get(0).getDoctor().getUsernameSpecial();
        this.wardName = patient.getWard().getName();
        this.gender = patient.getPatientData().getGender();
        this.age = patient.getPatientDate().getAge();
    }
}
