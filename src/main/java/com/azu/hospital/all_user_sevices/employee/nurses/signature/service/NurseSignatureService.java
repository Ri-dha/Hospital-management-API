package com.azu.hospital.all_user_sevices.employee.nurses.signature.service;


import com.azu.hospital.all_user_sevices.employee.nurses.signature.dao.NurseSignatureDao;
import com.azu.hospital.all_user_sevices.employee.nurses.signature.entity.NurseSignature;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NurseSignatureService {

    private final NurseSignatureDao nurseSignatureDao;

    @Autowired
    public NurseSignatureService(
            @Qualifier("NurseSignature") NurseSignatureDao nurseSignatureDao) {
        this.nurseSignatureDao = nurseSignatureDao;
    }


//    public void createNewNurseSignature(Long userId, NurseSignature request){
//        Optional<NurseSignature> newNurseSignature = nurseSignatureDao.getNurseSignatureByNurseId(userId);
//
//        if (newNurseSignature.isPresent()) {
//            throw new DuplicateResourceException("Nurse already has a signature");
//        }
//
//        NurseSignature nurseSignature = newNurseSignature.get();
//                new NurseSignature(
//                request.getFingerprint()
//               // request.getNurseId()
//        );
//        nurseSignatureDao.createNurseSignature(nurseSignature);
//    }


    public void updateExistsSignature(Long signatureId, NurseSignature update){

        Optional<NurseSignature> newNurseSignature = nurseSignatureDao.getNurseSignature(signatureId);
        if (newNurseSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        NurseSignature nurseSignature = newNurseSignature.get();

        if (update != null){
            nurseSignature.setFingerprint(update.getFingerprint());
        }
        nurseSignatureDao.updateNurseSignature(nurseSignature);
    }


    public Optional<NurseSignature> getNurseSignature(Long signatureId){
        Optional<NurseSignature> newNurseSignature = nurseSignatureDao.getNurseSignature(signatureId);
        if (newNurseSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        return nurseSignatureDao.getNurseSignature(signatureId);
    }

    public Optional<NurseSignature> getNurseSignatureByFingerPrint(byte[] fingerprint){
        Optional<NurseSignature> signature =nurseSignatureDao.getNurseSignatureByFingerPrint(fingerprint);
        if (signature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this fingerprint");
        }
        return signature;
    }


    public void deleteNurseSignatureById(Long signatureId){
        Optional<NurseSignature> newNurseSignature = nurseSignatureDao.getNurseSignature(signatureId);
        if (newNurseSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        nurseSignatureDao.deleteNurseSignature(signatureId);
    }





}
