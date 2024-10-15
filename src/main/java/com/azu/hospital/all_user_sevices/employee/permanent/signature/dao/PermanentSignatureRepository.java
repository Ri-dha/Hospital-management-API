package com.azu.hospital.all_user_sevices.employee.permanent.signature.dao;

import com.azu.hospital.all_user_sevices.employee.permanent.signature.entity.PermanentSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface PermanentSignatureRepository extends JpaRepository<PermanentSignature, Long> {

    Optional<PermanentSignature> findPermanentSignaturesByFingerprint(byte[] fingerprint);
}
