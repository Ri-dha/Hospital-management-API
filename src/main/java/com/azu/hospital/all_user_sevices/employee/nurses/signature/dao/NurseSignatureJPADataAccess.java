package com.azu.hospital.all_user_sevices.employee.nurses.signature.dao;

import com.azu.hospital.all_user_sevices.employee.nurses.signature.entity.NurseSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("NurseSignature")
public class NurseSignatureJPADataAccess implements NurseSignatureDao {

    private final NurseSignatureRepository nurseSignatureRepository;

    @Autowired
    public NurseSignatureJPADataAccess(NurseSignatureRepository nurseSignatureRepository) {
        this.nurseSignatureRepository = nurseSignatureRepository;
    }

    @Override
    public void createNurseSignature(NurseSignature request) {
        nurseSignatureRepository.save(request);
    }

    @Override
    public Optional<NurseSignature> getNurseSignature(Long signatureId) {
        return nurseSignatureRepository.findById(signatureId);
    }

    @Override
    public void updateNurseSignature(NurseSignature update) {
        nurseSignatureRepository.save(update);
    }

    @Override
    public void deleteNurseSignature(Long signatureId) {
      nurseSignatureRepository.deleteById(signatureId);
    }

//    @Override
//    public Optional<NurseSignature> getNurseSignatureByNurseId(Long signatureId) {
//        return nurseSignatureRepository.findNurseSignaturesByNurseId(signatureId);
//    }

    @Override
    public Optional<NurseSignature> getNurseSignatureByFingerPrint(byte[] fingerprint) {
        return nurseSignatureRepository.findNurseSignaturesByFingerprint(fingerprint);
    }
}
