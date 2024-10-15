package com.azu.hospital.all_user_sevices.employee.nurses.signature.dao;

import com.azu.hospital.all_user_sevices.employee.nurses.signature.entity.NurseSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface NurseSignatureRepository extends JpaRepository<NurseSignature, Long> {

//    Optional<NurseSignature> findNurseSignaturesByNurseId(Long userId);


    Optional<NurseSignature> findNurseSignaturesByFingerprint(byte[] fingerprint);
}
