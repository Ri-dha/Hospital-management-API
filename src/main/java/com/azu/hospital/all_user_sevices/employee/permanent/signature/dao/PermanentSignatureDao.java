package com.azu.hospital.all_user_sevices.employee.permanent.signature.dao;


import com.azu.hospital.all_user_sevices.employee.permanent.signature.entity.PermanentSignature;

import java.util.Optional;

public interface PermanentSignatureDao {

    void createPermanentSignature(PermanentSignature request);

    Optional<PermanentSignature> getPermanentSignature(Long signatureId);

    void updatePermanentSignature(PermanentSignature update);

    void deletePermanentSignature(Long signatureId);

//    Optional<PermanentSignature> getPermanentSignatureByPermanentId(Long signatureId);

    Optional<PermanentSignature> getPermanentSignatureByFingerPrint(byte[] fingerprint);
}
