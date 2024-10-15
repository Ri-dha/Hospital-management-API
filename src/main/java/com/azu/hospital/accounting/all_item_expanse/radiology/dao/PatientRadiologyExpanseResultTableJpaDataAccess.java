package com.azu.hospital.accounting.all_item_expanse.radiology.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientRadiologyExpanseResultTableJpa")
public class PatientRadiologyExpanseResultTableJpaDataAccess implements PatientRadiologyExpanseResultTableDao {

    private final PatientRadiologyExpanseResultTableRepository repository;

    @Autowired
    public PatientRadiologyExpanseResultTableJpaDataAccess(PatientRadiologyExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientRadiologyExpanseResultTable(PatientRadiologyExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientRadiologyExpanseResultTable(PatientRadiologyExpanseResultTable update) {
       repository.save(update);
    }

    @Override
    public Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientRadiologyExpanseResultTable> getAllPatientRadiologyExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId , state);
    }

    @Override
    public Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableByRadiologyId(Long patientId) {
        return repository.findPatientDrugsExpanseResultTableByDrugId(patientId);
    }

    @Override
    public List<PatientRadiologyExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableByRadiologyIdAndPatientId(Long radiologyId, Long patientId) {
        return repository.findPatientRadiologyExpanseResultTableByRadiologyIdAndPatientId(radiologyId , patientId);
    }

    @Override
    public List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }


}
