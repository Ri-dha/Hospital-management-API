package com.azu.hospital.all_user_sevices.employee.doctors.signature.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.signature.entity.DoctorSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface DoctorSignatureRepository extends JpaRepository<DoctorSignature, Long> {

//    Optional<DoctorSignature> findDoctorSignaturesByDoctorId(Long doctorId);


    Optional<DoctorSignature> findDoctorSignaturesByFingerprint(byte[] fingerprint);
}
