package com.azu.hospital.accounting.all_item_expanse.consumables.service;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.dao.PatientExpansesExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.patient_expances.dao.PatientExpanseDao;
import com.azu.hospital.patient_expances.dao.PatientExpanseListDao;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientsExpansesResultTableServiceImp")
public class PatientsExpansesResultTableService implements IPatientsExpansesResultTableService {

    private final PatientExpansesExpanseResultTableDao dao;

    private final PatientExpanseListDao patientExpanseListDao;

    private final PatientExpanseDao patientExpanseDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;


    public PatientsExpansesResultTableService(
            @Qualifier("PatientExpansesExpanseResultTableJpa") PatientExpansesExpanseResultTableDao dao,
            PatientExpanseListDao patientExpanseListDao, PatientExpanseDao patientExpanseDao,
            PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.patientExpanseListDao = patientExpanseListDao;
        this.patientExpanseDao = patientExpanseDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }



    @Override
    public void addPatientExpansesExpanseResultTable(Long patientId) {
        List<PatientExpanse> patientExpanses = patientExpanseDao.findAllReceivedPatientExpanseRequestByPatientId(patientId);

        for (PatientExpanse patientExpanse : patientExpanses) {
            if (!patientExpanse.getIsArchived()) {
                processConsumableRequest(patientExpanse, patientId);
            }
        }

    }

    private void processConsumableRequest(PatientExpanse patientExpanse, Long patientId) {
        Long consumableId = patientExpanse.getConsumables().getConsumableId();
        BigDecimal price = patientExpanse.getConsumables().getPrice();
        Optional<PatientsExpansesResultTable> optionalResultTable =
                dao.getPatientExpansesResultTableByItemIdAndPriceAndPatientId(consumableId, price, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingConsumableRecord(patientExpanse, optionalResultTable.get());
        } else {
            createNewConsumableRecord(patientExpanse, patientId);
        }
        archiveConsumableRequest(patientExpanse);
    }

    private void createNewConsumableRecord(PatientExpanse patientExpanse, Long patientId) {
        PatientsExpansesResultTable newRecord = new PatientsExpansesResultTable();
        populateConsumableExpanseResultTable(newRecord, patientExpanse, patientExpanse.getItemCount());
        dao.createPatientExpansesExpanseResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalPrice());
    }

    private void updateExistingConsumableRecord(PatientExpanse patientExpanse, PatientsExpansesResultTable existingRecord) {
        int newItemCount = existingRecord.getItemCount() + patientExpanse.getItemCount();
        populateConsumableExpanseResultTable(existingRecord, patientExpanse, newItemCount);
        dao.updatePatientExpansesExpanseResultTable(existingRecord);
        updatePatientWallet(patientExpanse.getPatient().getId(), existingRecord.getTotalPrice());
    }

    private void populateConsumableExpanseResultTable(PatientsExpansesResultTable record, PatientExpanse patientExpanse, int count) {
        record.setItemId(patientExpanse.getConsumables().getConsumableId());
        record.setItemName(patientExpanse.getConsumables().getConsumableName());
        record.setItemCount(count);
        record.setItemPrice(patientExpanse.getConsumables().getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = patientExpanse.getConsumables().getPrice();
        record.setTotalPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(patientExpanse.getPatient());
    }

    private void archiveConsumableRequest(PatientExpanse patientExpanse) {
        patientExpanse.setIsArchived(true);
        patientExpanseDao.updatePatientExpanse(patientExpanse);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }





}
