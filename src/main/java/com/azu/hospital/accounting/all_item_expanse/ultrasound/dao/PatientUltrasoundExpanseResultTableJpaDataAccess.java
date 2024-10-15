package com.azu.hospital.accounting.all_item_expanse.ultrasound.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("PatientUltrasoundExpanseResultTableJpa")
public class PatientUltrasoundExpanseResultTableJpaDataAccess implements PatientUltrasoundExpanseResultTableDao {

    private final PatientUltrasoundExpanseResultTableRepository repository;

    @Autowired
    public PatientUltrasoundExpanseResultTableJpaDataAccess(PatientUltrasoundExpanseResultTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientUltrasoundExpanseResultTable(PatientUltrasoundExpanseResultTable request) {
        repository.save(request);
    }

    @Override
    public void updatePatientUltrasoundExpanseResultTable(PatientUltrasoundExpanseResultTable update) {
       repository.save(update);
    }

    @Override
    public Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientUltrasoundExpanseResultTable> getAllPatientUltrasoundExpanseResultTableByPatientId(Long patientId , StockBillState state) {
        return repository.findAllByPatientId(patientId  ,state);
    }

    @Override
    public Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableByRadiologyId(Long drugId) {
        return repository.findPatientDrugsExpanseResultTableByDrugId(drugId);
    }

    @Override
    public List<PatientUltrasoundExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId) {
        return repository.findAllPatientExpansesExpanseResultTableByPatientId(patientId);
    }

    @Override
    public Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableByRadiologyIdAndPatientId(Long ultrasoundId, Long patientId) {
        return repository.findPatientUltrasoundExpanseResultTableByRadiologyIdAndPatientId(ultrasoundId, patientId);
    }

    @Override
    public List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId) {
        return repository.findAllByPatientIdAndIsArchivedTrue(patientId);
    }

    @Override
    public List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date) {
        return repository.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patientId, date);
    }


}
