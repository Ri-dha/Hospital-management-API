package com.azu.hospital.radiology.external.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ExternalRadiologyTestRepository extends JpaRepository<ExternalRadiologyTest, Long> {


    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "external_radiology_tests" +
            " " +
            "i WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);


    @Query("select r from ExternalRadiologyTest r where lower(r.patientData.patientName) LIKE concat('%' ," +
            "lower(:patientName) " +
            ", '%') and r.state in :state and r.type in :types ")
    Page<ExternalRadiologyTest> getAllWithFilter(
            @Param("types") List<RadiologyTypeEnum> types ,
            @Param("state") List<RadiologyOrderState> state,
            @Param("patientName") String patientName,
            Pageable pageable
    );


    @Query("SELECT Count(e) FROM ExternalRadiologyTest e WHERE e.state IN :state AND e.type IN :type")
    Long countAllByStateInAndTypeIn(@Param("state") List<RadiologyOrderState> state,@Param("type") List<RadiologyTypeEnum> type);


    @Query("SELECT Count(e) FROM ExternalRadiologyTest e")
    Long countExternalTest();


    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum(Max(s.radiologyBillHandler.id), CAST(count (s) AS integer), s.type  , Max(s.radiologyBillHandler.price), CAST(Max(s.radiologyBillHandler.price) * CAST(count (s) AS integer) AS BIGDECIMAL ) ) FROM InternalRadiologyTest s WHERE s.patient.id = :patientId GROUP BY s.type")
    List<BillsDtoSum>sumAllExternalRadiologyQuantityForSamePatientId(@Param("patientId") Long patientId);

}