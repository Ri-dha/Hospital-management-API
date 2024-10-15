package com.azu.hospital.all_user_sevices.employee.doctors.signature.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.signature.entity.DoctorSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("DoctorSignature")
public class DoctorSignatureJPADataAccess implements DoctorSignatureDao {

    private final DoctorSignatureRepository doctorSignatureRepository;

    @Autowired
    public DoctorSignatureJPADataAccess(DoctorSignatureRepository doctorSignatureRepository) {
        this.doctorSignatureRepository = doctorSignatureRepository;
    }

    @Override
    public void createDoctorSignature(DoctorSignature request) {
        doctorSignatureRepository.save(request);
    }

    @Override
    public Optional<DoctorSignature> getDoctorSignature(Long signatureId) {
        return doctorSignatureRepository.findById(signatureId);
    }

    @Override
    public void updateDoctorSignature(DoctorSignature update) {
        doctorSignatureRepository.save(update);
    }

    @Override
    public void deleteDoctorSignature(Long signatureId) {
      doctorSignatureRepository.deleteById(signatureId);
    }

//    @Override
//    public Optional<DoctorSignature> getDoctorSignatureByDoctorId(Long signatureId) {
//        return doctorSignatureRepository.findDoctorSignaturesByDoctorId(signatureId);
//    }

    @Override
    public Optional<DoctorSignature> getDoctorSignatureByFingerPrint(byte[] fingerprint) {
        return doctorSignatureRepository.findDoctorSignaturesByFingerprint(fingerprint);
    }
}
