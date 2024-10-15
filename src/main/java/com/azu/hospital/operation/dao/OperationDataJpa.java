package com.azu.hospital.operation.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository("OperationJpa")
public class OperationDataJpa implements OperationDao {

    private final OperationRepository operationRepository;

    public OperationDataJpa(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }


    @Override
    public void createNewOperation(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public Page<Operation> getOperationsByPatientId(Long patientId, Pageable pageable) {
        return operationRepository.getAllByPatientId(patientId, pageable);
    }

    @Override
    public Page<Operation> getOperationsByDate(Instant date, List<OperationStates> states, Pageable pageable) {
        return operationRepository.getAllByDate(date, states, pageable);
    }

    @Override
    public List<BillsDtoSum<String>> getAllWithSum(Long patientId, List<OperationStates> operationStates) {
        return operationRepository.getAllWithSum(patientId, List.of(
                OperationStates.PatientReceived,
                OperationStates.BeforeRecover,
                OperationStates.Recovery,
                OperationStates.SentToWard,
                OperationStates.WaitingForWard));
    }

    @Override
    public List<Operation> getAllCompletedByPatientId(Long patientId, List<OperationStates> operationStates) {
        return operationRepository.getAllCompletedByPatientId(patientId, List.of(
                OperationStates.PatientReceived,
                OperationStates.BeforeRecover,
                OperationStates.Recovery,
                OperationStates.SentToWard,
                OperationStates.WaitingForWard));
    }

    @Override
    public Integer countAllByPatientId(Long patientId, String type) {
        return operationRepository.countAllByPatientId(patientId, type);
    }

    @Override
    public Long countAll() {
        return operationRepository.count();
    }

    @Override
    public Long countAllByStateAndMonth(OperationStates state, Instant month) {
        return operationRepository.countAllByStateAndMonth(state, month);
    }

    @Override
    public Long countAllByStateAndOperationDate(OperationStates state, Instant operationDate) {
        return operationRepository.countByStateAndDate(state, operationDate);
    }

    @Override
    public Long countAllByListOfStatesAndOperationDate(List<OperationStates> states, Instant operationDate) {
        return operationRepository.countByListOfStatesAndDate(states, operationDate);
    }

    @Override
    public Page<Operation> getAllByListOfStates(List<OperationStates> states, Pageable pageable) {
        return operationRepository.getAllByListOfStates(states,pageable);
    }

    @Override
    public Page<Patient> getAllPatientWhoHasOperationAndStateIsBeforeInOperation(List<OperationStates> states, Pageable pageable) {
        return operationRepository.getAllPatientWhoHasOperationAndStateIsBeforeInOperation(List.of(
                OperationStates.Waiting,
                OperationStates.InWard
        ),pageable);
    }

    @Override
    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    @Override
    public void updateOperation(Operation operation) {
        operationRepository.save(operation);
    }

}
