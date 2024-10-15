package com.azu.hospital.all_user_sevices.employee.nurses.signature.entity;

import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import jakarta.persistence.*;

@Entity
@Table(name = "nurse_signature")
public class NurseSignature {

    @Id
    @SequenceGenerator(
            name = "nurse_signature_id_sequence",
            sequenceName = "nurse_signature_id_sequence",
            allocationSize = 77
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long signatureId;

    private byte[] fingerprint;

//    private Long userId;

    private Boolean isSignatureBlocked;

    private Boolean isSignatureRevoked;

    @OneToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;



    public NurseSignature() {
    }

    public NurseSignature(Long signatureId, byte[] fingerprint, Long nurseId, Nurse nurse) {
        this.signatureId = signatureId;
        this.fingerprint = fingerprint;
        //this.userId = userId;
       // this.nurse = nurse;
    }

    public NurseSignature(byte[] fingerprint, Long nurseId) {
        this.fingerprint = fingerprint;
       // this.userId = userId;

    }

    public NurseSignature(byte[] fingerprint, Long nurseId, Nurse nurse) {
        this.fingerprint = fingerprint;
       // this.userId = userId;
       // this.nurse = nurse;
    }

    public NurseSignature(byte[] fingerprint) {
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

//    public Long getNurseId() {
//        return userId;
//    }
//
//    public void setNurseId(Long userId) {
//        this.userId = userId;
//    }

//    public Nurse getNurse() {
//        return nurse;
//    }
//
//    public void setNurse(Nurse nurse) {
//        this.nurse = nurse;
//    }
}
