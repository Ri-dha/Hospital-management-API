package com.azu.hospital.all_user_sevices.user_signature.service;

import com.azu.hospital.all_user_sevices.user_signature.dao.UserSignatureDao;
import com.azu.hospital.all_user_sevices.user_signature.entity.UserSignature;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignatureService {

    private final UserSignatureDao userSignatureDao;

    @Autowired
    public UserSignatureService(
            @Qualifier("UserSignature") UserSignatureDao userSignatureDao) {
        this.userSignatureDao = userSignatureDao;
    }


//    public void createNewUserSignature(Long userId, UserSignature request){
//        Optional<UserSignature> newUserSignature = userSignatureDao.getUserSignatureByUserId(userId);
//
//        if (newUserSignature.isPresent()) {
//            throw new DuplicateResourceException("User already has a signature");
//        }
//
//        UserSignature userSignature = newUserSignature.get();
//                new UserSignature(
//                request.getFingerprint()
//               // request.getUserId()
//        );
//        userSignatureDao.createUserSignature(userSignature);
//    }


    public void updateExistsSignature(Long signatureId, UserSignature update){

        Optional<UserSignature> newUserSignature = userSignatureDao.getUserSignature(signatureId);
        if (newUserSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        UserSignature userSignature = newUserSignature.get();

        if (update != null){
            userSignature.setFingerprint(update.getFingerprint());
        }
        userSignatureDao.updateUserSignature(userSignature);
    }


    public Optional<UserSignature> getUserSignature(Long signatureId){
        Optional<UserSignature> newUserSignature = userSignatureDao.getUserSignature(signatureId);
        if (newUserSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        return userSignatureDao.getUserSignature(signatureId);
    }

    public Optional<UserSignature> getUserSignatureByFingerPrint(byte[] fingerprint){
        Optional<UserSignature> signature =userSignatureDao.getUserSignatureByFingerPrint(fingerprint);
        if (signature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this fingerprint");
        }
        return signature;
    }


    public void deleteUserSignatureById(Long signatureId){
        Optional<UserSignature> newUserSignature = userSignatureDao.getUserSignature(signatureId);
        if (newUserSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        userSignatureDao.deleteUserSignature(signatureId);
    }





}
