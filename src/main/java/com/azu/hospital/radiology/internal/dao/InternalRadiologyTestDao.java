package com.azu.hospital.radiology.internal.dao;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface InternalRadiologyTestDao {

    List<DateTimeTest> getDateTimeTest(LocalDate date);

    Integer countOfTests(List<RadiologyOrderState> states, List<RadiologyTypeEnum> type);

    Optional<InternalRadiologyTest> getInternalRadiologyTestById(Long id);

    void updateInternalRadiologyTest(InternalRadiologyTest internalRadiologyTest);

    InternalRadiologyTest createInternalRadiologyTest(InternalRadiologyTest internalRadiologyTest);


    Page<InternalRadiologyTest> getAllByFilter(
            List<RadiologyTypeEnum> types,
            String search,
            List<RadiologyOrderState> states,
            Pageable pageable);

    Page<InternalRadiologyTest> getAllConsultantTest(Pageable pageable);

    Page<InternalRadiologyTest> getByPatientId(Long patientId, List<RadiologyTypeEnum> types, List<RadiologyOrderState> states, Pageable pageable);

    List<InternalRadiologyTest> getAllByConsultantPatientId(Long patientId);


    List<InternalRadiologyTest> getAllCompletedByPatientId(Long patientId, List<RadiologyOrderState> states);

    List<BillsDtoSum<RadiologyTypeEnum>> sumAllDrugsItemQuantityForSamePatientId(Long patientId);

    List<InternalRadiologyTest> getAllCompletedByPatientId(Long patientId);

    Integer countAllByPatientId(Long patientId, RadiologyTypeEnum type);

}
