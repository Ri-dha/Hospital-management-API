package com.azu.hospital.ultrasound.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundBillDto;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.ultrasound.ultrasoundBill.dto.UltrasoundBillDto;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface InternalUltrasoundTestRepository extends JpaRepository<InternalUltrasoundTest, Long> {


    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "internal_ultrasound_tests" +
            " " +
            "i WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);


    @Query("select r from InternalUltrasoundTest r where r.patient.patientData.fullName LIKE concat('%' ," +
            "lower(:patientName) " +
            ", '%') and r.state in :state and r.testDestination = :testDestination and r.type in :types order by r.createdAt desc")
    Page<InternalUltrasoundTest> getAllWithFilter(
            @Param("types") List<UltrasoundTypeEnum> types,
            @Param("state") List<UltrasoundOrderState> state,
            @Param("patientName") String patientName,
            @Param("testDestination") TestDestination testDestination,
            Pageable pageable
    );

    @Query("SELECT i FROM InternalUltrasoundTest i WHERE i.patient.id = :patientId" +
            " and i.type in :types and i.state in :states  order by i.createdAt desc")
    Page<InternalUltrasoundTest> getByPatientId(Long patientId,
                                               List<UltrasoundTypeEnum> types,
                                               List<UltrasoundOrderState> states,
                                               Pageable pageable );

    @Query("select r from InternalUltrasoundTest r where r.patient.id = :patientId and r.state in :state ")
    Page<InternalUltrasoundTest> getAllByPatientIdAndState(
            @Param("patientId") Long patientId,
            @Param("state") List<UltrasoundOrderState> state,
            Pageable pageable
    );




    Integer countAllByStateIn(List<UltrasoundOrderState> state);

    Page<InternalUltrasoundTest> getAllByTestDestination(TestDestination destination,  Pageable pageable);
    List<InternalUltrasoundTest> getAllByConsultantPatientIdAndTestDestination(Long patientId ,
                                                                           TestDestination destination);

    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum(Max(s.ultrasoundBill.id), CAST(count (s) AS " +
            "integer), s.type , " +
            "Max(s.ultrasoundBill.price), CAST(Max(s.ultrasoundBill.price) * CAST(count (s) AS integer) AS BIGDECIMAL ) )" +
            " FROM InternalUltrasoundTest s" +
            " WHERE s.patient.id = :patientId AND s.state = 'Completed' GROUP BY s.type")
    List<BillsDtoSum<UltrasoundTypeEnum>> sumAllInternalUltrasoundQuantityForSamePatientId(@Param("patientId") Long patientId);



    @Query("select r from InternalUltrasoundTest r where r.patient.id = :patientId" +
            " and r.state = 'Completed' And r.isArchived = false")
    List<InternalUltrasoundTest> getAllCompletedByPatientId(Long patientId);

//    @Query("SELECT count (i) FROM InternalUltrasoundTest i WHERE i.type =:type and i.patient.id = :patientId and i.state = com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState.Completed and i.isArchived = false")
//    Integer countAllByPatientId(Long patientId , UltrasoundTypeEnum type);
}
