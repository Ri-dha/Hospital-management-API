package com.azu.hospital.operation.dao;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import com.azu.hospital.operation.dto.OperationDto;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface OperationDao {

    void createNewOperation(Operation operation);

    Optional<Operation> findById(Long id);
    void updateOperation(Operation operation);


    Page<Operation> getOperationsByPatientId(Long patientId , Pageable pageable);
    Page<Operation> getOperationsByDate(Instant date , List<OperationStates> states, Pageable pageable);


    List<BillsDtoSum<String>> getAllWithSum(Long patientId, List<OperationStates> operationStates);

    List<Operation> getAllCompletedByPatientId(Long patientId,List<OperationStates> operationStates);

    Integer countAllByPatientId(Long patientId,String type);

    Long countAll();

    Long countAllByStateAndMonth(OperationStates state, Instant month);

    Long countAllByStateAndOperationDate(OperationStates state, Instant operationDate);

    Long countAllByListOfStatesAndOperationDate(List<OperationStates> states, Instant operationDate);

    Page<Operation> getAllByListOfStates(List<OperationStates> states,Pageable pageable);

    Page<Patient> getAllPatientWhoHasOperationAndStateIsBeforeInOperation(List<OperationStates> states, Pageable pageable);

}
