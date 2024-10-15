package com.azu.hospital.accounting.all_item_expanse.Labs.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.Labs.dao.PatientLabExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientLabExpanseResultTableServiceImp")
public class PatientLabExpanseResultTableService implements IPatientLabExpanseResultTableService {
    private final PatientLabExpanseResultTableDao dao;
    private final IntTestRequestDao intTestRequestDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;

    @Autowired
    public PatientLabExpanseResultTableService(
            @Qualifier("PatientLabExpanseResultTableJpa") PatientLabExpanseResultTableDao dao,
            @Qualifier("internalTestRequestRepo") IntTestRequestDao intTestRequestDao,
            PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.intTestRequestDao = intTestRequestDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }


    @Override
    public void addPatientLabExpanseResultTable(Long patientId) {

        List<IntTestRequest> labList = intTestRequestDao.getAllCompletedByPatientId(patientId);

        for (IntTestRequest lab : labList) {
            if (!lab.getIsArchived()) {
                processLabTest(lab, patientId);
            }
        }
    }


    private void processLabTest(IntTestRequest lab, Long patientId) {
        Long labId = lab.getTestNameForMainTest().getTestId();
        Optional<PatientLabExpanseResultTable> optionalResultTable =
                dao.getPatientLabExpanseResultTableByLabIdAndPatientId(labId, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingLabRecord(lab, optionalResultTable.get(), patientId);
        } else {
            createNewLabRecord(lab, patientId);
        }
        archiveLabTest(lab);
    }

    private void createNewLabRecord(IntTestRequest lab, Long patientId) {
        PatientLabExpanseResultTable newRecord = new PatientLabExpanseResultTable();
        populateLabExpanseResultTable(newRecord, lab, 1);
        dao.createPatientLabExpanseResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalLabTestPrice());
    }

    private void updateExistingLabRecord(IntTestRequest lab, PatientLabExpanseResultTable existingRecord, Long patientId) {
        int newCount = existingRecord.getLabTestCount() + 1;
        populateLabExpanseResultTable(existingRecord, lab, newCount);
        dao.updatePatientLabExpanseResultTable(existingRecord);
        updatePatientWallet(patientId, existingRecord.getTotalLabTestPrice());
    }

    private void populateLabExpanseResultTable(PatientLabExpanseResultTable record, IntTestRequest lab, int count) {
        record.setLabTestId(lab.getTestNameForMainTest().getTestId());
        record.setLabTestName(lab.getTestNameForMainTest().getTestName());
        record.setLabTestCount(count);
        record.setLabTestPrice(lab.getTestNameForMainTest().getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = lab.getTestNameForMainTest().getPrice();
        record.setTotalLabTestPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(lab.getInternalLabTest().getPatient());
    }

    private void archiveLabTest(IntTestRequest lab) {
        lab.setIsArchived(true);
        intTestRequestDao.updateTestRequest(lab);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }
}




