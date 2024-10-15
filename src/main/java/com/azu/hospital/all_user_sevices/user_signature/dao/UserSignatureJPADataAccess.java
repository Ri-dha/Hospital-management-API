package com.azu.hospital.all_user_sevices.user_signature.dao;

import com.azu.hospital.all_user_sevices.user_signature.entity.UserSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserSignature")
public class UserSignatureJPADataAccess implements UserSignatureDao{

    private final UserSignatureRepository userSignatureRepository;

    @Autowired
    public UserSignatureJPADataAccess(UserSignatureRepository userSignatureRepository) {
        this.userSignatureRepository = userSignatureRepository;
    }

    @Override
    public void createUserSignature(UserSignature request) {
        userSignatureRepository.save(request);
    }

    @Override
    public Optional<UserSignature> getUserSignature(Long signatureId) {
        return userSignatureRepository.findById(signatureId);
    }

    @Override
    public void updateUserSignature(UserSignature update) {
        userSignatureRepository.save(update);
    }

    @Override
    public void deleteUserSignature(Long signatureId) {
      userSignatureRepository.deleteById(signatureId);
    }

//    @Override
//    public Optional<UserSignature> getUserSignatureByUserId(Long signatureId) {
//        return userSignatureRepository.findUserSignaturesByUserId(signatureId);
//    }

    @Override
    public Optional<UserSignature> getUserSignatureByFingerPrint(byte[] fingerprint) {
        return userSignatureRepository.findUserSignaturesByFingerprint(fingerprint);
    }
}
