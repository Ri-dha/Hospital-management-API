package com.azu.hospital.all_user_sevices.user_signature.dao;

import com.azu.hospital.all_user_sevices.user_signature.entity.UserSignature;

import java.util.Optional;

public interface UserSignatureDao {

    void createUserSignature(UserSignature request);

    Optional<UserSignature> getUserSignature(Long signatureId);

    void updateUserSignature(UserSignature update);

    void deleteUserSignature(Long signatureId);

//    Optional<UserSignature> getUserSignatureByUserId(Long signatureId);

    Optional<UserSignature> getUserSignatureByFingerPrint(byte[] fingerprint);
}
