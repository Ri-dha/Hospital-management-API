package com.azu.hospital.all_user_sevices.employee.doctors.signature.service;


import com.azu.hospital.all_user_sevices.employee.doctors.signature.dao.DoctorSignatureDao;
import com.azu.hospital.all_user_sevices.employee.doctors.signature.entity.DoctorSignature;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorSignatureService {

    private final DoctorSignatureDao doctorSignatureDao;

    @Autowired
    public DoctorSignatureService(
            @Qualifier("DoctorSignature") DoctorSignatureDao doctorSignatureDao) {
        this.doctorSignatureDao = doctorSignatureDao;
    }


//    public void createNewDoctorSignature(Long doctorId, DoctorSignature request){
//        Optional<DoctorSignature> newDoctorSignature = doctorSignatureDao.getDoctorSignatureByDoctorId(doctorId);
//
//        if (newDoctorSignature.isPresent()) {
//            throw new DuplicateResourceException("Doctor already has a signature");
//        }
//
//        DoctorSignature doctorSignature = newDoctorSignature.get();
//                new DoctorSignature(
//                request.getFingerprint()
//               // request.getDoctorId()
//        );
//        doctorSignatureDao.createDoctorSignature(doctorSignature);
//    }


    public void updateExistsSignature(Long signatureId, DoctorSignature update){

        Optional<DoctorSignature> newDoctorSignature = doctorSignatureDao.getDoctorSignature(signatureId);
        if (newDoctorSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        DoctorSignature doctorSignature = newDoctorSignature.get();

        if (update != null){
            doctorSignature.setFingerprint(update.getFingerprint());
        }
        doctorSignatureDao.updateDoctorSignature(doctorSignature);
    }


    public Optional<DoctorSignature> getDoctorSignature(Long signatureId){
        Optional<DoctorSignature> newDoctorSignature = doctorSignatureDao.getDoctorSignature(signatureId);
        if (newDoctorSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        return doctorSignatureDao.getDoctorSignature(signatureId);
    }

    public Optional<DoctorSignature> getDoctorSignatureByFingerPrint(byte[] fingerprint){
        Optional<DoctorSignature> signature =doctorSignatureDao.getDoctorSignatureByFingerPrint(fingerprint);
        if (signature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this fingerprint");
        }
        return signature;
    }


    public void deleteDoctorSignatureById(Long signatureId){
        Optional<DoctorSignature> newDoctorSignature = doctorSignatureDao.getDoctorSignature(signatureId);
        if (newDoctorSignature.isEmpty()) {
            throw new ResourceNotFoundException("There is no signature for this id");
        }
        doctorSignatureDao.deleteDoctorSignature(signatureId);
    }





}
