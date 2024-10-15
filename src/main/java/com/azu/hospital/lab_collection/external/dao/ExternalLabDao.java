package com.azu.hospital.lab_collection.external.dao;

import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExternalLabDao {

    List<DateTimeTest> getDateTimeTest(LocalDate date);

    Page<ExternalLabTest> getAllExternalTest(List<LabRequestStatusEnum> states , Pageable pageable);
    Page<ExternalLabTest> getAllExternalTestNotArchived(Pageable pageable);
    Long countAll();
    Page<ExternalLabTest> getAllExternalTestArchived(Pageable pageable);

    ExternalLabTest createNewExternalTest(ExternalLabTest externalLabTest);

    Optional<ExternalLabTest> findById(Long id);

    void  updateExternalTest(ExternalLabTest externalLabTest);

    Optional<ExternalLabTest> getExternalLabByRequestId(Long labId);
}
