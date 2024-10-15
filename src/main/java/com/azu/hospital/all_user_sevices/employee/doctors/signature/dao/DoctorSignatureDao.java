package com.azu.hospital.all_user_sevices.employee.doctors.signature.dao;


import com.azu.hospital.all_user_sevices.employee.doctors.signature.entity.DoctorSignature;

import java.util.Optional;

public interface DoctorSignatureDao {

    void createDoctorSignature(DoctorSignature request);

    Optional<DoctorSignature> getDoctorSignature(Long signatureId);

    void updateDoctorSignature(DoctorSignature update);

    void deleteDoctorSignature(Long signatureId);

//    Optional<DoctorSignature> getDoctorSignatureByDoctorId(Long signatureId);

    Optional<DoctorSignature> getDoctorSignatureByFingerPrint(byte[] fingerprint);
}
