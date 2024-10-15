package com.azu.hospital.all_user_sevices.employee.permanent.signature.service;


import com.azu.hospital.all_user_sevices.employee.permanent.signature.dao.PermanentSignatureDao;
import com.azu.hospital.all_user_sevices.employee.permanent.signature.entity.PermanentSignature;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermanentSignatureService {

    private final PermanentSignatureDao permanentSignatureDao;

    @Autowired
    public PermanentSignatureService(
            @Qualifier("PermanentSignature") PermanentSignatureDao permanentSignatureDao) {
        this.permanentSignatureDao = permanentSignatureDao;
    }


//    public void createNewPermanentSignature(Long permanentId, PermanentSignature request){
//        Optional<PermanentSignature> newPermanentSignature = permanentSignatureDao.getPermanentSignatureByPermanentId(permanentId);
//
//        if (newPermanentSignature.isPresent()) {
//            throw new DuplicateResourceException("Permanent already has a signature");
//        }
//
//        PermanentSignature permanentSignature = newPermanentSignature.get();
//                new PermanentSignature(
//                request.getFingerprint()
//               // request.getPermanentId()
//        );
//        permanentSignatureDao.createPermanentSignature(permanentSignature);
//    }


    public void updateExistsSignature(Long signatureId, PermanentSignature update){

        Optional<PermanentSignature> newPermanentSignature = permanentSignatureDao.getPermanentSignature(signatureId);
        if (newPermanentSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        PermanentSignature permanentSignature = newPermanentSignature.get();

        if (update != null){
            permanentSignature.setFingerprint(update.getFingerprint());
        }
        permanentSignatureDao.updatePermanentSignature(permanentSignature);
    }


    public Optional<PermanentSignature> getPermanentSignature(Long signatureId){
        Optional<PermanentSignature> newPermanentSignature = permanentSignatureDao.getPermanentSignature(signatureId);
        if (newPermanentSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        return permanentSignatureDao.getPermanentSignature(signatureId);
    }

    public Optional<PermanentSignature> getPermanentSignatureByFingerPrint(byte[] fingerprint){
        Optional<PermanentSignature> signature =permanentSignatureDao.getPermanentSignatureByFingerPrint(fingerprint);
        if (signature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this fingerprint");
        }
        return signature;
    }


    public void deletePermanentSignatureById(Long signatureId){
        Optional<PermanentSignature> newPermanentSignature = permanentSignatureDao.getPermanentSignature(signatureId);
        if (newPermanentSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        permanentSignatureDao.deletePermanentSignature(signatureId);
    }





}
