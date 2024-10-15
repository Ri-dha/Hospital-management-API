package com.azu.hospital.all_user_sevices.employee.doctors.signature.entity;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import jakarta.persistence.*;

@Entity
@Table(name = "doctor_signature")
public class DoctorSignature {

    @Id
    @SequenceGenerator(
            name = "doctor_signature_id_sequence",
            sequenceName = "doctor_signature_id_sequence",
            allocationSize = 77
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long signatureId;

    private byte[] fingerprint;

//    private Long doctorId;

    private Boolean isSignatureBlocked;

    private Boolean isSignatureRevoked;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



    public DoctorSignature() {
    }

    public DoctorSignature(Long signatureId, byte[] fingerprint, Long doctorId, Doctor doctor) {
        this.signatureId = signatureId;
        this.fingerprint = fingerprint;
        //this.doctorId = doctorId;
       // this.doctor = doctor;
    }

    public DoctorSignature(byte[] fingerprint, Long doctorId) {
        this.fingerprint = fingerprint;
       // this.doctorId = doctorId;

    }

    public DoctorSignature(byte[] fingerprint, Long doctorId, Doctor doctor) {
        this.fingerprint = fingerprint;
       // this.doctorId = doctorId;
       // this.doctor = doctor;
    }

    public DoctorSignature(byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Long getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(Long signatureId) {
        this.signatureId = signatureId;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }

//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
}
