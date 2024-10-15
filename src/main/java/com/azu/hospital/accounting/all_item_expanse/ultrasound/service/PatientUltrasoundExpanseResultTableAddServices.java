package com.azu.hospital.accounting.all_item_expanse.ultrasound.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.dao.PatientUltrasoundExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.dao.PatientWalletDao;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("PatientUltrasoundExpanseResultTableAddServicesImp")
public class PatientUltrasoundExpanseResultTableAddServices implements IPatientUltrasoundExpanseResultTableAddServices {
    private final PatientUltrasoundExpanseResultTableDao dao;

    private final InternalUltrasoundTestDao internalUltrasoundTestDao;
    private final PatientDao patientDao;


    private final IPatientWalletService iPatientWalletService;

    @Autowired
    public PatientUltrasoundExpanseResultTableAddServices(
            @Qualifier("PatientUltrasoundExpanseResultTableJpa") PatientUltrasoundExpanseResultTableDao dao,
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }


    public void addPatientUltrasoundExpanseResultTable(Long patientId) {
        List<InternalUltrasoundTest> ultrasoundList = internalUltrasoundTestDao.getAllCompletedByPatientId(patientId);
        for (InternalUltrasoundTest ultrasound : ultrasoundList) {
            if (!ultrasound.getIsArchived()) {
                processUltrasoundTest(ultrasound, patientId);
            }
        }
    }

    private void processUltrasoundTest(InternalUltrasoundTest ultrasound, Long patientId) {
        Long ultrasoundId = ultrasound.getUltrasoundBill().getId();
        Optional<PatientUltrasoundExpanseResultTable> optionalResultTable =
                dao.getPatientUltrasoundExpanseResultTableByRadiologyIdAndPatientId(ultrasoundId, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingRecord(ultrasound, optionalResultTable.get(), patientId);
        } else {
            createNewRecord(ultrasound, patientId);
        }
        archiveUltrasoundTest(ultrasound);
    }


    private void createNewRecord(InternalUltrasoundTest ultrasound, Long patientId) {
        PatientUltrasoundExpanseResultTable newRecord = new PatientUltrasoundExpanseResultTable();
        populateUltrasoundExpanseResultTable(newRecord, ultrasound, 1);
        dao.createPatientUltrasoundExpanseResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalUltrasoundPrice());
    }

    private void updateExistingRecord(InternalUltrasoundTest ultrasound, PatientUltrasoundExpanseResultTable existingRecord, Long patientId) {
        int newCount = existingRecord.getUltrasoundCount() + 1;
        populateUltrasoundExpanseResultTable(existingRecord, ultrasound, newCount);
        dao.updatePatientUltrasoundExpanseResultTable(existingRecord);
        updatePatientWallet(patientId, existingRecord.getTotalUltrasoundPrice());
    }

    private void populateUltrasoundExpanseResultTable(PatientUltrasoundExpanseResultTable record, InternalUltrasoundTest ultrasound, int count) {
        record.setUltrasoundId(ultrasound.getUltrasoundBill().getId());
        record.setUltrasoundName(UltrasoundTypeEnum.valueOf(ultrasound.getType().name()));
        record.setUltrasoundCount(count);
        record.setUltrasoundPrice(ultrasound.getUltrasoundBill().getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = ultrasound.getUltrasoundBill().getPrice();
        record.setTotalUltrasoundPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(ultrasound.getPatient());
    }

    private void archiveUltrasoundTest(InternalUltrasoundTest ultrasound) {
        ultrasound.setIsArchived(true);
        internalUltrasoundTestDao.updateInternalUltrasoundTest(ultrasound);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }


}
