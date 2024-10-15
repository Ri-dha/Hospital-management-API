package com.azu.hospital.radiology.internal.dao;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Transactional
public interface InternalRadiologyTestRepository extends JpaRepository<InternalRadiologyTest, Long> {

    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "internal_radiology_tests" +
            " " +
            "i WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);

    @Query("SELECT COUNT(i) FROM InternalRadiologyTest i WHERE i.state IN :state AND i.type IN :type")
    Integer countAllByStateInAndTypeIn(List<RadiologyOrderState> state, Collection<RadiologyTypeEnum> type);


    @Query("select r from InternalRadiologyTest r where r.patient.patientData.fullName LIKE concat('%' ," +
            "lower(:patientName) " +
            ", '%') and r.state in :state and r.testDestination = :testDestination and r.type in :types order by r.createdAt desc")
    Page<InternalRadiologyTest> getAllWithFilter(
            @Param("types") List<RadiologyTypeEnum> types,
            @Param("patientName") String patientName,
            @Param("state") List<RadiologyOrderState> state,
            @Param("testDestination") TestDestination testDestination,
            Pageable pageable
    );

    @Query("SELECT i FROM InternalRadiologyTest i WHERE i.testDestination = :destination")
    Page<InternalRadiologyTest> getAllByTestDestination(TestDestination destination, Pageable pageable);

    @Query("SELECT i FROM InternalRadiologyTest i WHERE i.patient.id = :patientId" +
            " and i.type in :types and i.state in :states  order by i.createdAt desc")
    Page<InternalRadiologyTest> getByPatientId(Long patientId,List<RadiologyTypeEnum> types,List<RadiologyOrderState> states, Pageable pageable );

    @Query("SELECT i FROM InternalRadiologyTest i" +
            " WHERE i.consultantPatient.id = :patientId AND i.testDestination = :testDestination")
    List<InternalRadiologyTest> getAllByConsultantPatientIdAndTestDestination(Long patientId,
                                                                              TestDestination testDestination);

    @Query("select r from InternalRadiologyTest r where r.patient.id = :patientId and r.state in :state ")
    List<InternalRadiologyTest> getAllByPatientIdAndState(
            @Param("patientId") Long patientId,
            @Param("state") List<RadiologyOrderState> state
    );


    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum" +
            "(Max(s.radiologyBillHandler.id), CAST(count (s) AS integer)," +
            " s.type  , Max(s.radiologyBillHandler.price)," +
            " CAST(Max(s.radiologyBillHandler.price) * CAST(count (s) AS integer) AS BIGDECIMAL ) )" +
            " FROM InternalRadiologyTest s WHERE s.patient.id = :patientId AND s.state = 4 GROUP BY s.type")
    List<BillsDtoSum<RadiologyTypeEnum>>sumAllInternalRadiologyQuantityForSamePatientId(@Param("patientId") Long patientId);


    @Query("SELECT internalTest FROM InternalRadiologyTest internalTest WHERE internalTest.patient.id = :patientId" +
            " AND internalTest.state = 4 AND internalTest.isArchived = false")
    List<InternalRadiologyTest> findCompletedRadiologyTestsByPatientId(Long patientId);


    @Query("SELECT count (i) FROM InternalRadiologyTest i WHERE i.type=:type and i.patient.id = :patientId and i.state = com.azu.hospital.utils.enums.radiology.RadiologyOrderState.Completed and i.isArchived = false")
    Integer countAllByPatientId(Long patientId, RadiologyTypeEnum type);


}
