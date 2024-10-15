package com.azu.hospital.accounting.all_item_expanse.drugs.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.drugs.dao.PatientDrugsExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientDrugsExpanseResultTableAddServicesImp")
public class PatientDrugsExpanseResultTableAddServices implements IPatientDrugsExpanseResultTableAddServices {

    private final PatientDrugsExpanseResultTableDao dao;
    private final DrugRequestHandlerDao handlerDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;

    public PatientDrugsExpanseResultTableAddServices(
            @Qualifier("PatientDrugsExpanseResultTableJpa") PatientDrugsExpanseResultTableDao dao, DrugRequestHandlerDao handlerDao, PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.handlerDao = handlerDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }


    @Override
    public void addPatientDrugsExpanseResultTable(Long patientId) {
        List<DrugRequestHandler> handlerList = handlerDao.getAllReceivedDrugsRequestByPatentId(patientId);

        for (DrugRequestHandler handler : handlerList) {
            if (!handler.getIsArchived()) {
                processDrugRequest(handler, patientId);
            }
        }
    }

    private void processDrugRequest(DrugRequestHandler handler, Long patientId) {
        Long drugId = handler.getDrugsItems().getId();
        BigDecimal price = handler.getDrugsItems().getDrugSellingPrice();
        Optional<PatientDrugsExpanseResultTable> optionalResultTable =
                dao.getPatientDrugsExpanseResultTableByDrugIdAndPriceAndPatientId(drugId, price, patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingDrugRecord(handler, optionalResultTable.get());
        } else {
            createNewDrugRecord(handler, patientId);
        }
        archiveDrugRequest(handler);
    }

    private void createNewDrugRecord(DrugRequestHandler handler, Long patientId) {
        PatientDrugsExpanseResultTable newRecord = new PatientDrugsExpanseResultTable();
        populateDrugExpanseResultTable(newRecord, handler, handler.getQuantity());
        dao.createPatientDrugsExpanseResultTable(newRecord);
    }

    private void updateExistingDrugRecord(DrugRequestHandler handler, PatientDrugsExpanseResultTable existingRecord) {
        int newDrugCount = existingRecord.getDrugCount() + handler.getQuantity();
        populateDrugExpanseResultTable(existingRecord, handler, newDrugCount);
        dao.updatePatientDrugsExpanseResultTable(existingRecord);
    }

    private void populateDrugExpanseResultTable(PatientDrugsExpanseResultTable record, DrugRequestHandler handler, int count) {

        record.setDrugName(handler.getDrugsItems().getItemName());
        record.setDrugCount(count);
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = handler.getDrugsItems().getDrugSellingPrice();
        record.setPatient(handler.getPatient());
    }

    private void archiveDrugRequest(DrugRequestHandler handler) {
        handler.setIsArchived(true);
        handlerDao.updateDrugRequest(handler);
    }

    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }

}

