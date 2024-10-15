package com.azu.hospital.accounting.patient_new_wallet.services;


import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import com.azu.hospital.accounting.patient_new_wallet.dao.PatientNewWalletDao;
import com.azu.hospital.accounting.patient_new_wallet.request.PatientNewWalletUpdateRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientNewWalletUpdateServices {
    private final PatientNewWalletDao dao;
    private final ExceptionsMessageReturn messageReturn;

    public PatientNewWalletUpdateServices(PatientNewWalletDao dao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.messageReturn = messageReturn;
    }


    public void updatePatientNewWallet(Long id,PatientNewWalletUpdateRequest request
    ){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        PatientNewWallet patientNewWallet = dao.getPatientNewWalletById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + " " + id
                ));

        boolean changes = false;
        if(request.operationId() != null){
            patientNewWallet.setPatientIdm(request.operationId());
            changes = true;
        }
        if(request.userId() != null){
            patientNewWallet.setUserId(request.userId());
            changes = true;
        }
        if(request.patientId() != null){
            patientNewWallet.setPatientIdm(request.patientId());
            changes = true;
        }
        if(request.patientName() != null){
            patientNewWallet.setPatientName(request.patientName());
            changes = true;
        }
        if(request.operationName() != null){
            patientNewWallet.setOperationName(request.operationName());
            changes = true;
        }
        if(request.userName() != null){
            patientNewWallet.setUserName(request.userName());
            changes = true;
        }
        if(request.note() != null){
            patientNewWallet.setNote(request.note());
            changes = true;
        }
        if(request.operationCost() != null){
            patientNewWallet.setOperationCost(request.operationCost());
            changes = true;
        }
        if(request.doctorPercentage() != null){
            patientNewWallet.setDoctorPercentage(request.doctorPercentage());
            changes = true;
        }
        if(!changes){
            throw new IllegalArgumentException(
                    messages.getString("noChanges")
            );
        }
        dao.updatePatientNewWallet(patientNewWallet);
    }
}
