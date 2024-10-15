package com.azu.hospital.radiology.external.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExternalRadiologyTestDao {
    List<DateTimeTest> getDateTimeTest(LocalDate date);

    Long countOfTests(List<RadiologyOrderState> states, List<RadiologyTypeEnum> types);

    Optional<ExternalRadiologyTest> findById(Long id);

    void updateExternalRadiologyTest(ExternalRadiologyTest externalRadiologyTest);

    ExternalRadiologyTest createExternalRadiologyTest(ExternalRadiologyTest externalRadiologyTest);

    Page<ExternalRadiologyTest> getAllByFilter(
            List<RadiologyTypeEnum> types ,
            List<RadiologyOrderState> states,
            String search,
            Pageable pageable
    );

    List<BillsDtoSum> getAllWithSum(Long patientId);


}
