package com.azu.hospital.accounting.all_item_expanse.radiology.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.radiology.dao.PatientRadiologyExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientRadiologyExpanseResultTableAddServicesImp")
public class PatientRadiologyExpanseResultTableAddServices implements IPatientRadiologyExpanseResultTableAddServices {
    private final PatientRadiologyExpanseResultTableDao dao;

    private final InternalRadiologyTestDao internalRadiologyTestDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;

    @Autowired
    public PatientRadiologyExpanseResultTableAddServices(
            @Qualifier("PatientRadiologyExpanseResultTableJpa") PatientRadiologyExpanseResultTableDao dao,
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }

    @Override
    public void addPatientRadiologyExpanseResultTable(Long patientId) {

        List<InternalRadiologyTest> radiologyList = internalRadiologyTestDao.getAllCompletedByPatientId(patientId);
        for (InternalRadiologyTest radiology : radiologyList) {
            if (!radiology.getIsArchived()) {
                processRadiologyTest(radiology, patientId);
            }
        }
    }

    private void processRadiologyTest(InternalRadiologyTest radiology, Long patientId) {
        Long radiologyId = radiology.getRadiologyBillHandler().getId();
        Optional<PatientRadiologyExpanseResultTable> optionalResultTable =
                dao.getPatientRadiologyExpanseResultTableByRadiologyIdAndPatientId(radiologyId, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingRadiologyRecord(radiology, optionalResultTable.get(), patientId);
        } else {
            createNewRadiologyRecord(radiology, patientId);
        }
        archiveRadiologyTest(radiology);
    }

    private void createNewRadiologyRecord(InternalRadiologyTest radiology, Long patientId) {
        PatientRadiologyExpanseResultTable newRecord = new PatientRadiologyExpanseResultTable();
        populateRadiologyExpanseResultTable(newRecord, radiology, 1);
        dao.createPatientRadiologyExpanseResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalRadiologyPrice());
    }

    private void updateExistingRadiologyRecord(InternalRadiologyTest radiology, PatientRadiologyExpanseResultTable
            existingRecord, Long patientId) {
        int newCount = existingRecord.getRadiologyCount() + 1;
        populateRadiologyExpanseResultTable(existingRecord, radiology, newCount);
        dao.updatePatientRadiologyExpanseResultTable(existingRecord);
        updatePatientWallet(patientId, existingRecord.getTotalRadiologyPrice());
    }

    private void populateRadiologyExpanseResultTable(PatientRadiologyExpanseResultTable record, InternalRadiologyTest radiology, int count) {
        record.setRadiologyId(radiology.getRadiologyBillHandler().getId());
        record.setRadiologyName(RadiologyTypeEnum.valueOf(radiology.getType().name()));
        record.setRadiologyCount(count);
        record.setRadiologyPrice(radiology.getRadiologyBillHandler().getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = radiology.getRadiologyBillHandler().getPrice();
        record.setTotalRadiologyPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(radiology.getPatient());
    }

    private void archiveRadiologyTest(InternalRadiologyTest radiology) {
        radiology.setIsArchived(true);
        internalRadiologyTestDao.updateInternalRadiologyTest(radiology);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }


}





