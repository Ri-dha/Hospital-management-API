package com.azu.hospital.accounting.patient_wallet.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.add_priceses.services.IPrintFinalListServices;
import com.azu.hospital.accounting.patient_wallet.dao.PatientWalletDao;
import com.azu.hospital.accounting.patient_wallet.dto.PatientWalletDto;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.request.PatientTransactionRequest;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.services.IPatientTransactionService;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.PatientTransactionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service("PatientWalletServiceImp")
public class PatientWalletService implements IPatientWalletService {


    private final PatientDao patientDao;

    private final PatientWalletDao patientWalletDao;

    private final IPatientTransactionService patientTransactionService;
    private final ExceptionsMessageReturn messageReturn;

    private final IPrintFinalListServices printFinalListServices;

    public PatientWalletService(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("PatientWalletDataJpa") PatientWalletDao patientWalletDao,
            @Qualifier("patientTransactionService") IPatientTransactionService patientTransactionService,
            ExceptionsMessageReturn messageReturn, @Qualifier("PrintFinalListServicesImp") IPrintFinalListServices printFinalListServices) {
        this.patientDao = patientDao;
        this.patientWalletDao = patientWalletDao;
        this.patientTransactionService = patientTransactionService;
        this.messageReturn = messageReturn;
        this.printFinalListServices = printFinalListServices;
    }

    @Override
    public void createNewPatientWallet(Patient patient) {
        patientWalletDao.createNewPatientWallet(new PatientWallet(patient, BigDecimal.ZERO));
    }

    @Override
    public void addBalance(PatientWalletRequest request) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(request.patientId())
                .orElseThrow(
                            () -> new ResourceNotFoundException(messages.getString("patientNotFound"))
                );

        Optional<PatientWallet> patientWallet = patientWalletDao.getPatientWalletByPatientId(patient.getId());


        if (patientWallet.isEmpty()) {
            PatientWallet newPatientWallet = patientWalletDao.createNewPatientWallet(new PatientWallet(patient, BigDecimal.ZERO));
            newPatientWallet.setBalance(newPatientWallet.getBalance().add(request.amount()));
            patientWalletDao.updatePatientWallet(newPatientWallet);

            patientTransactionService.addTransaction(new PatientTransactionRequest(newPatientWallet.getId(),
                    request.amount(), PatientTransactionType.Recharge));
        } else {

            if ( patientWallet.get().getBalance().compareTo(request.amount()) < 0) {
                throw new BadRequestException("balance is not enough");
            }

            patientWallet.get().setBalance(patientWallet.get().getBalance().add(request.amount()));
            patientWalletDao.updatePatientWallet(patientWallet.get());

            // implement pay button process
            printFinalListServices.payButtonProcess(patient, patientWallet.get());


            // add transaction log
            patientTransactionService.addTransaction(new PatientTransactionRequest(patientWallet.get().getId(),
                    request.amount(), PatientTransactionType.Recharge));

        }
    }

    @Override
    public void payCutBalance(PatientWalletRequest request) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(request.patientId())
                .orElseThrow(
                () -> new ResourceNotFoundException(messages.getString("patientNotFound"))
        );

        Optional<PatientWallet> patientWallet = patientWalletDao.getPatientWalletByPatientId(patient.getId());

        if (patientWallet.isEmpty()) {

            PatientWallet newPatientWallet = patientWalletDao.createNewPatientWallet(new PatientWallet(patient, BigDecimal.ZERO));
            newPatientWallet.setBalance(newPatientWallet.getBalance().subtract(request.amount()));

            patientWalletDao.updatePatientWallet(newPatientWallet);

            patientTransactionService.addTransaction(new PatientTransactionRequest(newPatientWallet.getId(),
                    request.amount(), PatientTransactionType.PayCut));
        } else {
            patientWallet.get().setBalance(patientWallet.get().getBalance().subtract(request.amount()));

            patientWalletDao.updatePatientWallet(patientWallet.get());

            patientTransactionService.addTransaction(new PatientTransactionRequest(patientWallet.get().getId(),
                    request.amount(), PatientTransactionType.PayCut));
        }

    }


    public PatientWalletDto getPatientWallet(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                () -> new ResourceNotFoundException(messages.getString("patientNotFound"))
        );

        Optional<PatientWallet> patientWallet = patientWalletDao.getPatientWalletByPatientId(patient.getId());

        if (patientWallet.isEmpty()) {
            PatientWallet newPatientWallet = patientWalletDao.createNewPatientWallet(new PatientWallet(patient, BigDecimal.ZERO));
            return new PatientWalletDto(newPatientWallet.getId(), newPatientWallet.getBalance());
        } else {
            return new PatientWalletDto(patientWallet.get().getId(), patientWallet.get().getBalance());
        }
    }
}
