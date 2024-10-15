package com.azu.hospital.all_user_sevices.user_signature.entity;

import com.azu.hospital.all_user_sevices.user_entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_signature")
public class UserSignature {

    @Id
    @SequenceGenerator(
            name = "user_signature_id_sequence",
            sequenceName = "user_signature_id_sequence",
            allocationSize = 77
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long signatureId;

    private byte[] fingerprint;

//    private Long userId;

    private Boolean isSignatureBlocked;

    private Boolean isSignatureRevoked;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



    public UserSignature() {
    }

    public UserSignature(Long signatureId, byte[] fingerprint, Long userId, User user) {
        this.signatureId = signatureId;
        this.fingerprint = fingerprint;
        //this.userId = userId;
       // this.user = user;
    }

    public UserSignature(byte[] fingerprint, Long userId) {
        this.fingerprint = fingerprint;
       // this.userId = userId;

    }

    public UserSignature(byte[] fingerprint, Long userId, User user) {
        this.fingerprint = fingerprint;
       // this.userId = userId;
       // this.user = user;
    }

    public UserSignature(byte[] fingerprint) {
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

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
