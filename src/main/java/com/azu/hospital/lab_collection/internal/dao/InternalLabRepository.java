package com.azu.hospital.lab_collection.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDto;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.InternalLabTestsBillsDto;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public interface InternalLabRepository extends JpaRepository<InternalLabTest, Long> {

    Page<InternalLabTest> getAllByStateIsNotInAndLabTestDestinationNotOrderByPatientMedicalHistoryTriage(
            List<InternalLabRequestStatusEnum> state,
            TestDestination destination,
            Pageable pageable
    );

    @Query("SELECT t FROM InternalLabTest t WHERE t.state IN :state AND t.labTestDestination = :destination ORDER BY t.createdAt DESC")
    List<InternalLabTest> getAllByStateIsAndLabTestDestinationOrderByCreatedAtDesc(List<InternalLabRequestStatusEnum> state,
                                                                TestDestination destination , Pageable pageable);

    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "internal_lab_test i" +
            " WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);


    Page<InternalLabTest> getAllByLabTestDestination(TestDestination destination , Pageable pageable);

    List<InternalLabTest> getAllByLabTestDestinationAndConsultantPatientId(TestDestination destination ,
                                                                           Long patientId);

    @Query("SELECT count (i) from InternalLabTest i where DATE(i.createdAt) = CURRENT_DATE")
    Long countAllBy();

    @Query("SELECT i FROM InternalLabTest i where i.state in:states")
    Page<InternalLabTest> getAllByStates(List<InternalLabRequestStatusEnum> states , Pageable pageable);


    @Query("SELECT i FROM InternalLabTest i where i.patient.id = :patientId order by i.state desc ")
    Page<InternalLabTest> getAllByPatientId(@Param("patientId") Long patientId, Pageable pageable);

    @Query("SELECT i FROM InternalLabTest i JOIN i.testRequests r JOIN r.intTestResults ir WHERE i.id = :labId")
    Optional<InternalLabTest> findByRequestId(@Param("labId") Long requestId);

    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum" +
            "(Max(s.testNameForMainTest.testId), CAST(count (s) AS integer), s.testName  , Max(s.testNameForMainTest.price)," +
            " CAST(Max(s.testNameForMainTest.price) * CAST(count(s) AS integer) AS BIGDECIMAL ) ) FROM" +
            " IntTestRequest s WHERE s.internalLabTest.patient.id = :patientId AND s.internalLabTest.state = com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum.Complete GROUP BY s.testName")
    List<BillsDtoSum<String>> getAllWithSum(Long patientId);

}
