package com.azu.hospital.accounting.all_item_expanse.consumables.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientExpansesExpanseResultTableJpa")
public class PatientExpansesExpanseResultTableJpaDataAccess implements PatientExpansesExpanseResultTableDao {

    private final PatientExpansesResultTableRepository repository;

    @Autowired
    public PatientExpansesExpanseResultTableJpaDataAccess(PatientExpansesResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientExpansesExpanseResultTable(PatientsExpansesResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientExpansesExpanseResultTable(PatientsExpansesResultTable update) {
       repository.save(update);
    }

    @Override
    public Optional<PatientsExpansesResultTable> getPatientExpansesExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientsExpansesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public List<PatientsExpansesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientsExpansesResultTable> getPatientExpansesExpanseResultTableByDrugId(Long patientId) {
        return repository.findPatientExpansesExpanseResultTableByExpansesId(patientId);
    }

    @Override
    public List<BillsDtoSum<String>> sumExpansesQuantityPatientId(Long patientId) {
        return null;
    }

    @Override
    public Optional<PatientsExpansesResultTable> getPatientExpansesResultTableByItemIdAndPriceAndPatientId(Long consumableId, BigDecimal price, Long patientI) {
        return repository.findPatientExpansesResultTableByItemIdAndPriceAndPatientId(consumableId, price, patientI);
    }

    @Override
    public List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }
}
