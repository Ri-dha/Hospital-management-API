package com.azu.hospital.all_user_sevices.employee.permanent.signature.dao;

import com.azu.hospital.all_user_sevices.employee.permanent.signature.entity.PermanentSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PermanentSignature")
public class PermanentSignatureJPADataAccess implements PermanentSignatureDao {

    private final PermanentSignatureRepository permanentSignatureRepository;

    @Autowired
    public PermanentSignatureJPADataAccess(PermanentSignatureRepository permanentSignatureRepository) {
        this.permanentSignatureRepository = permanentSignatureRepository;
    }

    @Override
    public void createPermanentSignature(PermanentSignature request) {
        permanentSignatureRepository.save(request);
    }

    @Override
    public Optional<PermanentSignature> getPermanentSignature(Long signatureId) {
        return permanentSignatureRepository.findById(signatureId);
    }

    @Override
    public void updatePermanentSignature(PermanentSignature update) {
        permanentSignatureRepository.save(update);
    }

    @Override
    public void deletePermanentSignature(Long signatureId) {
      permanentSignatureRepository.deleteById(signatureId);
    }


    @Override
    public Optional<PermanentSignature> getPermanentSignatureByFingerPrint(byte[] fingerprint) {
        return permanentSignatureRepository.findPermanentSignaturesByFingerprint(fingerprint);
    }
}
