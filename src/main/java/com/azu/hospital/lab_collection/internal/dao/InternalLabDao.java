package com.azu.hospital.lab_collection.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.InternalLabTestsBillsDto;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InternalLabDao {

    Long countAllInternalLab();

    List<DateTimeTest> getDateTimeTest(LocalDate date);

    InternalLabTest createNewInternalTest(InternalLabTest internalLabTest);
    Long countAll();

    Optional<InternalLabTest> findById(Long id);

    void updateInternalTest(InternalLabTest internalLabTest);

    Page<InternalLabTest> getAllConsultantTest(Pageable pageable);

    List<InternalLabTest> getAllByConsultantPatientId(Long patientId);

    Page<InternalLabTest> getAllByState(List<InternalLabRequestStatusEnum> states , Pageable pageable);
    Page<InternalLabTest> getAllByPatientId(Long patientId , Pageable pageable);

    Optional<InternalLabTest> getInternalLabByRequestId(Long labId);

    List<BillsDtoSum<String>> getAllInternalLabWithSum(Long patientId);

}
