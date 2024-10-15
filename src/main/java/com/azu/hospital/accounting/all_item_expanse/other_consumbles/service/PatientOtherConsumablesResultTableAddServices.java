package com.azu.hospital.accounting.all_item_expanse.other_consumbles.service;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao.PatientOtherConsumablesResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("PatientOtherConsumablesResultTableAddServices")
public class PatientOtherConsumablesResultTableAddServices implements IPatientOtherConsumablesResultTableService {

    private final PatientOtherConsumablesResultTableDao dao;

    private final OtherConsumablesDao otherConsumablesDao;
    private final PatientDao patientDao;

    private final IPatientWalletService iPatientWalletService;

    @Autowired
    public PatientOtherConsumablesResultTableAddServices(
            @Qualifier("PatientOtherConsumablesResultTableJpa") PatientOtherConsumablesResultTableDao dao,
            OtherConsumablesDao otherConsumablesDao,
            PatientDao patientDao, IPatientWalletService iPatientWalletService) {
        this.dao = dao;
        this.otherConsumablesDao = otherConsumablesDao;
        this.patientDao = patientDao;
        this.iPatientWalletService = iPatientWalletService;
    }

    @Override
    public void addPatientOtherConsumablesResultTable(Long patientId) {
        List<OtherConsumables> otherConsumables = otherConsumablesDao.getAllOtherConsumables(patientId);
        for (OtherConsumables otherConsumable : otherConsumables) {
            if (!otherConsumable.getIsArchived()) {
                processOtherConsumables(otherConsumable, patientId);
            }
        }
    }



    private void processOtherConsumables(OtherConsumables otherConsumable, Long patientId) {
        Long otherConsumableId = otherConsumable.getOtherConsumablesId();
        Optional<PatientOtherConsumablesResultTable> optionalResultTable =
                dao.getPatientOtherConsumablesResultTableByDrugIdAndPatientId(otherConsumableId,patientId);

        if (optionalResultTable.isPresent()) {
            updateExistingOtherConsumablesRecord(otherConsumable, optionalResultTable.get(), patientId);
        } else {
            createNewOtherConsumablesRecord(otherConsumable, patientId);
        }
        archiveOtherConsumables(otherConsumable);
    }

    private void createNewOtherConsumablesRecord(OtherConsumables otherConsumable, Long patientId) {
        PatientOtherConsumablesResultTable newRecord = new PatientOtherConsumablesResultTable();
        populateOtherConsumablesResultTable(newRecord, otherConsumable, otherConsumable.getCount());
        dao.createPatientOtherConsumablesResultTable(newRecord);
        updatePatientWallet(patientId, newRecord.getTotalPrice());
    }

    private void updateExistingOtherConsumablesRecord(OtherConsumables otherConsumable, PatientOtherConsumablesResultTable
            existingRecord, Long patientId) {
        int newCount = existingRecord.getItemCount() + otherConsumable.getCount();
        populateOtherConsumablesResultTable(existingRecord, otherConsumable, newCount);
        dao.updatePatientOtherConsumablesResultTable(existingRecord);
        updatePatientWallet(patientId, existingRecord.getTotalPrice());
    }

    private void archiveOtherConsumables(OtherConsumables otherConsumable) {
        otherConsumable.setIsArchived(true);
        otherConsumablesDao.updateOtherConsumables(otherConsumable);
    }
    private void populateOtherConsumablesResultTable(PatientOtherConsumablesResultTable
                                                             record, OtherConsumables otherConsumable, int count) {
        record.setItemId(otherConsumable.getId());
        record.setItemName(otherConsumable.getName());
        record.setItemCount(count);
        record.setItemPrice(otherConsumable.getPrice());
        BigDecimal totalQuantity = BigDecimal.valueOf(count);
        BigDecimal totalPrice = otherConsumable.getPrice();
        record.setTotalPrice(totalQuantity.multiply(totalPrice));
        record.setPatient(otherConsumable.getPatient());
    }
    private void updatePatientWallet(Long patientId, BigDecimal amount) {
        iPatientWalletService.payCutBalance(new PatientWalletRequest(patientId, amount));
    }

    @Override
    public void update(Long itemId,Long patientId,BigDecimal price, int count,String name){
        Optional<PatientOtherConsumablesResultTable> optionalResultTable = dao.findByNameAndPatientId(itemId, patientId);
        if (optionalResultTable.isPresent()) {
            PatientOtherConsumablesResultTable patientOtherConsumablesResultTable = optionalResultTable.get();
            patientOtherConsumablesResultTable.setItemPrice(price);
            patientOtherConsumablesResultTable.setItemCount(count);
            patientOtherConsumablesResultTable.setItemName(name);
            patientOtherConsumablesResultTable.setTotalPrice(price.multiply(BigDecimal.valueOf(patientOtherConsumablesResultTable.getItemCount())));
            dao.updatePatientOtherConsumablesResultTable(patientOtherConsumablesResultTable);
    }else {
            throw new ResourceNotFoundException("Other Consumables not found with name : " + itemId);
        }
        updatePatientWallet(patientId, price.multiply(BigDecimal.valueOf(count)));
    }
}
