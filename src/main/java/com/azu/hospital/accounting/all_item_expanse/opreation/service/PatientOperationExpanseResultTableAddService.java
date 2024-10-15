package com.azu.hospital.accounting.all_item_expanse.opreation.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.opreation.dao.PatientOperationExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.opreation.dao.PatientOperationExpanseResultTableJpaDataAccess;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientOperationExpanseResultTableAddServiceImp")
public class PatientOperationExpanseResultTableAddService implements IPatientOperationExpanseResultTableAddService {

    private final PatientOperationExpanseResultTableDao dao;

    private final OperationDao operationDao;

    private final IPatientWalletService iPatientWalletService;
    private final PatientDao patientDao;

    @Autowired
    public PatientOperationExpanseResultTableAddService(
            @Qualifier("PatientOperationExpanseResultTableJpa") PatientOperationExpanseResultTableDao patientOperationExpanseResultTableDao,
            @Qualifier("OperationJpa") OperationDao operationDao, IPatientWalletService iPatientWalletService, PatientDao patientDao) {
        this.dao = patientOperationExpanseResultTableDao;
        this.operationDao = operationDao;
        this.iPatientWalletService = iPatientWalletService;
        this.patientDao = patientDao;
    }


    @Override
    public void addPatientOperationExpanseResultTable(Long patientId) {
        List<Operation> operationList = operationDao.getAllCompletedByPatientId(patientId, List.of(
                OperationStates.PatientReceived,
                OperationStates.BeforeRecover,
                OperationStates.Recovery,
                OperationStates.SentToWard,
                OperationStates.WaitingForWard));

        for (Operation operation : operationList) {
            if (!operation.getIsArchived()) {
                processOperation(operation, patientId);
            }
        }

    }

    private void processOperation(Operation operation, Long patientId) {
        Long operationId = operation.getSurgical().getId();
        Optional<PatientOperationExpanseResultTable> optionalResultTable =
                dao.getPatientOperationExpanseResultTableByOperationIdAndPatientId(operationId, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingOperationRecord(operation, optionalResultTable.get(), patientId);
        } else {
            createNewOperationRecord(operation, patientId);
        }
        archiveOperation(operation);
    }

    private void createNewOperationRecord(Operation operation, Long patientId) {
        PatientOperationExpanseResultTable newRecord = new PatientOperationExpanseResultTable();
        populateOperationExpanseResultTable(newRecord, operation, 1);
        dao.createPatientOperationExpanseResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalOperationPrice());
    }

    private void updateExistingOperationRecord(Operation operation, PatientOperationExpanseResultTable existingRecord, Long patientId) {
        int newCount = existingRecord.getOperationCount() + 1;
        populateOperationExpanseResultTable(existingRecord, operation, newCount);
        dao.updatePatientOperationExpanseResultTable(existingRecord);
        updatePatientWallet(patientId, existingRecord.getTotalOperationPrice());
    }

    private void populateOperationExpanseResultTable(PatientOperationExpanseResultTable record, Operation operation, int count) {
        record.setOperationId(operation.getSurgical().getId());
        record.setOperationName(operation.getSurgical().getSurgicalName());
        record.setOperationCount(count);
        record.setOperationPrice(operation.getSurgical().getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = operation.getSurgical().getPrice();
        record.setTotalOperationPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(operation.getPatient());
    }

    private void archiveOperation(Operation operation) {
        operation.setIsArchived(true);
        operationDao.updateOperation(operation);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }
}



