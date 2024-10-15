package com.azu.hospital.all_user_sevices.employee.permanent.signature.entity;

import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import jakarta.persistence.*;

@Entity
@Table(name = "permanent_signature")
public class PermanentSignature {

    @Id
    @SequenceGenerator(
            name = "permanent_signature_id_sequence",
            sequenceName = "permanent_signature_id_sequence",
            allocationSize = 77
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long signatureId;

    private byte[] fingerprint;

//    private Long permanentId;

    private Boolean isSignatureBlocked;

    private Boolean isSignatureRevoked;

    @OneToOne
    @JoinColumn(name = "permanent_id")
    private Permanent permanent;



    public PermanentSignature() {
    }

    public PermanentSignature(Long signatureId, byte[] fingerprint, Long permanentId, Permanent permanent) {
        this.signatureId = signatureId;
        this.fingerprint = fingerprint;
        //this.permanentId = permanentId;
       // this.permanent = permanent;
    }

    public PermanentSignature(byte[] fingerprint, Long permanentId) {
        this.fingerprint = fingerprint;
       // this.permanentId = permanentId;

    }

    public PermanentSignature(byte[] fingerprint, Long permanentId, Permanent permanent) {
        this.fingerprint = fingerprint;
       // this.permanentId = permanentId;
       // this.permanent = permanent;
    }

    public PermanentSignature(byte[] fingerprint) {
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

//    public Long getPermanentId() {
//        return permanentId;
//    }
//
//    public void setPermanentId(Long permanentId) {
//        this.permanentId = permanentId;
//    }

//    public Permanent getPermanent() {
//        return permanent;
//    }
//
//    public void setPermanent(Permanent permanent) {
//        this.permanent = permanent;
//    }
}
