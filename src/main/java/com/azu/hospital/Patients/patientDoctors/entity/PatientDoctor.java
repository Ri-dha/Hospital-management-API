package com.azu.hospital.Patients.patientDoctors.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "patient_doctors")
@Getter
@Setter
public class PatientDoctor {

    @Id
    @SequenceGenerator(name = "patient_doctor_id_seq" , sequenceName = "patient_doctor_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor ;


    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "patient_id")
    private Patient patient ;

//    @ManyToOne(fetch = FetchType.LAZY )
//    @JoinColumn(name = "patient_archive_id")
//    private PatientArchive patientArchive ;
//todo: patient archive is not working

    private PatientDoctorState state;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;
    public PatientDoctor() {
    }


    public PatientDoctor(PatientDoctorState state) {
        this.state = state;
    }

    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
