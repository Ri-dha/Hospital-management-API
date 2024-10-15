package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.services;

import com.azu.hospital.accounting.patient_wallet.dao.PatientWalletDao;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dao.PatientTransactionDao;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto.PatientTransactionDto;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto.PatientTransactionDtoMapper;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.request.PatientTransactionRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.PatientTransactionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientTransactionService implements IPatientTransactionService {


    private final PatientTransactionDao patientTransactionDao;

    private final PatientWalletDao patientWalletDao;

    private final PatientTransactionDtoMapper mapper;

    private final ExceptionsMessageReturn messageReturn;



    public PatientTransactionService(
            @Qualifier("PatientTransactionDao") PatientTransactionDao patientTransactionDao,
            @Qualifier("PatientWalletDataJpa") PatientWalletDao patientWalletDao,
            PatientTransactionDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.patientTransactionDao = patientTransactionDao;
        this.patientWalletDao = patientWalletDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    @Override
    public void addTransaction(PatientTransactionRequest request) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        PatientWallet patientWallet = patientWalletDao.findById(request.patientWalletId())
                .orElseThrow(
                            () -> new ResourceNotFoundException(messages.getString("patientWalletNotFound"))
                );
        PatientTransactionList patientTransactionList = new PatientTransactionList();

       patientTransactionList.setPatientWallet(patientWallet);
       patientTransactionList.setAmount(request.amount());
       patientTransactionList.setType(request.type());
       patientTransactionDao.addTransaction(patientTransactionList);
    }


    @Override
    public List<PatientTransactionDto> getPatientTransactionList(Long patientId) {
        return patientTransactionDao.getPatientTransactionList(patientId)
                .stream()
                .map(mapper)
                .toList();
    }
}
