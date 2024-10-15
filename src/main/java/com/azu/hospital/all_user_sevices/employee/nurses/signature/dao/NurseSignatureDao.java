package com.azu.hospital.all_user_sevices.employee.nurses.signature.dao;


import com.azu.hospital.all_user_sevices.employee.nurses.signature.entity.NurseSignature;

import java.util.Optional;

public interface NurseSignatureDao {

    void createNurseSignature(NurseSignature request);

    Optional<NurseSignature> getNurseSignature(Long signatureId);

    void updateNurseSignature(NurseSignature update);

    void deleteNurseSignature(Long signatureId);

//    Optional<NurseSignature> getNurseSignatureByNurseId(Long signatureId);

    Optional<NurseSignature> getNurseSignatureByFingerPrint(byte[] fingerprint);
}
